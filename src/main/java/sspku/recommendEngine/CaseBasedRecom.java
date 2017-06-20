package sspku.recommendEngine;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import sspku.dao.Job;
import sspku.dao.UserInfoWithBLOBs;
import sspku.dto.Expection;
import sspku.mapper.UserInfoMapper;
import sspku.util.Tuple;

@Service
public class CaseBasedRecom implements IRecAlgorithms {

	@Autowired
	private ExtractCandidateJobUtil extractUtil;
	@Autowired
	private UserInfoMapper userMapper;

	public ExtractCandidateJobUtil getExtractUtil() {
		return extractUtil;
	}

	public void setExtractUtil(ExtractCandidateJobUtil extractUtil) {
		this.extractUtil = extractUtil;
	}

	public UserInfoMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserInfoMapper userMapper) {
		this.userMapper = userMapper;
	}

	public Map<String, Double> predict(int userId, int topNum) {
		List<Job> jobs = extractUtil.getCandidatedJob(userId);
		List<Record> candidates = extractUtil.tranformJobToRecord(jobs, userId);
		List<Record> expectedCaseJob = this.getExpectedCase(userId);
		if (expectedCaseJob == null) {
			return new HashMap<String, Double>();
		}
		Map<String, DoubleSummaryStatistics> boundaryMap = this.getMaxAndMin(candidates);
		Map<String, TreeSet<Double>> rawMap = new HashMap<>();
		for (Record expect : expectedCaseJob) {
			for (Record can : candidates) {
				double score = this.predict(expect, can, boundaryMap);
				if(!rawMap.containsKey(can.getKey())){
					rawMap.put(can.getKey(), new TreeSet<Double>());
				}
				rawMap.get(can.getKey()).add(score);
			}
		}
		Map<String, Double> resultMap = new LinkedHashMap<>();
		rawMap.entrySet().stream().forEach(entry -> {
			resultMap.put(entry.getKey(), entry.getValue().last());
		});
		return resultMap.entrySet().stream().sorted(Map.Entry.<String, Double> comparingByValue().reversed())
				.limit(topNum).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	public double predict(Record expect, Record item, Map<String, DoubleSummaryStatistics> boundaryMap) {
		return this.similarity(expect, item, boundaryMap);
	}

	private Map<String, DoubleSummaryStatistics> getMaxAndMin(List<Record> candidates) {
		Map<String, DoubleSummaryStatistics> libData = candidates.stream()
				.flatMap(r -> r.getValueLIB().entrySet().stream()).collect(Collectors.groupingBy(Map.Entry::getKey,
						Collectors.summarizingDouble(i -> i.getValue().value)));
		Map<String, DoubleSummaryStatistics> mibData = candidates.stream()
				.flatMap(r -> r.getValueMIB().entrySet().stream()).collect(Collectors.groupingBy(Map.Entry::getKey,
						Collectors.summarizingDouble(i -> i.getValue().value)));
		libData.putAll(mibData);
		return libData;
	}

	private double similarity(Record expect, Record item, Map<String, DoubleSummaryStatistics> boundaryMap) {
		double score = 0;
		if (expect.getValueMIB() != null) {
			for (String k : expect.getValueMIB().keySet()) {
				if (item.getValueMIB().containsKey(k)
						&& item.getValueMIB().get(k).getValue() > expect.getValueMIB().get(k).getValue()) {
					score += expect.getValueMIB().get(k).getWeight() * this.simMIB(boundaryMap.get(k).getMax(),
							expect.getValueMIB().get(k).getValue(), item.getValueMIB().get(k).getValue());
				}
			}
		}
		if (expect.getValueLIB() != null) {
			for (String k : expect.getValueLIB().keySet()) {
				if (item.getValueLIB().containsKey(k)
						&& item.getValueLIB().get(k).getValue() < expect.getValueLIB().get(k).getValue()) {
					score += expect.getValueLIB().get(k).getWeight() * this.simLIB(boundaryMap.get(k).getMin(),
							expect.getValueLIB().get(k).getValue(), item.getValueLIB().get(k).getValue());
				}
			}
		}
		if (expect.getValueStrEq() != null) {
			for (String k : expect.getValueStrEq().keySet()) {
				if (item.getValueStrEq().containsKey(k) && item.getValueStrEq().get(k).getValue() != null
						&& expect.getValueStrEq().get(k).getValue() != null
						&& item.getValueStrEq().get(k).value.equals(expect.getValueStrEq().get(k).value)) {
					score += expect.getValueStrEq().get(k).getWeight();
				}
			}
		}
		if (expect.getValueStrContains() != null) {
			for (String k : expect.getValueStrContains().keySet()) {
				if (item.getValueStrContains().containsKey(k)) {
					String itemStr = item.getValueStrContains().get(k).getValue();
					String expectStr = expect.getValueStrContains().get(k).getValue();
					if (itemStr != null && expectStr != null
							&& (itemStr.contains(expectStr) || expectStr.contains(itemStr))) {
						score += expect.getValueStrContains().get(k).getWeight();
					}
				}
			}
		}
		if (expect.getValueStrLeven() != null) {
			for (String k : expect.getValueStrLeven().keySet()) {
				if (item.getValueLIB().containsKey(k)) {
					String itemStr = item.getValueStrLeven().get(k).getValue();
					String expectStr = expect.getValueStrLeven().get(k).getValue();
					if (!Strings.isNullOrEmpty(itemStr) && !Strings.isNullOrEmpty(expectStr)) {
						score += expect.getValueStrLeven().get(k).getWeight()
								* StringUtils.getLevenshteinDistance(itemStr, expectStr);
					}
				}
			}
		}
		return score;
	}

	private double simLIB(double minR, double exR, double itemR) {
		if (exR - itemR <= 0)
			return 0;
		double sim = (double) (exR - itemR) / (exR - minR);
		return sim;
	}

	private double simMIB(double maxR, double exR, double itemR) {
		if (itemR - exR <= 0)
			return 0;
		double sim = (double) (itemR - exR) / (maxR - exR);
		return sim;
	}

	private List<Record> getExpectedCase(int userId) {
		UserInfoWithBLOBs user = userMapper.selectByPrimaryKey(userId);
		if (Strings.isNullOrEmpty(user.getExpection())) {
			return null;
		}
		List<Expection> expects = JSON.parseArray(user.getExpection(), Expection.class);
		List<Record> records = new ArrayList<>();
		expects.forEach(e -> {
			records.add(Record.getRecordByExpection(e));
		});
		return records;
	}

}
