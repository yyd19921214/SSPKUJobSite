package sspku.recommendEngine;

import java.util.ArrayList;
import java.util.Arrays;
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

@Service
public class ConstraintBasedRecom implements IRecAlgorithms {

	@Autowired
	private ExtractCandidateJobUtil extractUtil;
	@Autowired
	private UserInfoMapper userMapper;

	private static final double leven_threshold = 0.7;

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
		List<Record> constraints = this.getConstraintList(userId);
		Map<String, TreeSet<Double>> rawMap = new HashMap<>();
		for (Record expect : constraints) {
			for (Record can : candidates) {
				double score = this.predict(expect, can);
				if(!rawMap.containsKey(can.getKey())){
					rawMap.put(can.getKey(), new TreeSet<Double>());
				}
				rawMap.get(can.getKey()).add(score);
			}
		}

		Map<String, Double> resultMap = new LinkedHashMap<>();
		for (String k : rawMap.keySet()) {
			resultMap.put(k, rawMap.get(k).last());
		}
		return resultMap.entrySet().stream().sorted(Map.Entry.<String, Double> comparingByValue().reversed())
				.limit(topNum).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	public double predict(Record constraint, Record item) {
		return this.similarity(constraint, item);
	}

	private double similarity(Record constraint, Record item) {
		double score = 0;
		for (String k : constraint.getValueMIB().keySet()) {
			if (item.getValueMIB().containsKey(k)
					&& item.getValueMIB().get(k).getValue() > constraint.getValueMIB().get(k).getValue()) {
				score += constraint.getValueMIB().get(k).getWeight();
			}
		}
		for (String k : constraint.getValueLIB().keySet()) {
			if (item.getValueLIB().containsKey(k)
					&& item.getValueLIB().get(k).getValue() < constraint.getValueLIB().get(k).getValue()) {
				score += constraint.getValueLIB().get(k).getWeight();
			}
		}
		for (String k : constraint.getValueStrEq().keySet()) {
			if(item.getValueStrEq().containsKey(k)){
				String s=item.getValueStrEq().get(k).getValue();
				String s2=constraint.getValueStrEq().get(k).getValue();
				if(s.equals(s2)){
					score += constraint.getValueStrEq().get(k).getWeight();
				}
			}
//			if (item.getValueStrEq().containsKey(k)
//					&& constraint.getValueStrEq().get(k).equals(item.getValueStrEq().get(k).getValue())) {
//				score += constraint.getValueStrEq().get(k).getWeight();
//			}
		}
		for (String k : constraint.getValueStrContains().keySet()) {
			if (item.getValueStrContains().containsKey(k)) {
				String conStr = constraint.getValueStrContains().get(k).getValue();
				String itemStr = item.getValueStrContains().get(k).getValue();
				if (!Strings.isNullOrEmpty(itemStr) && !Strings.isNullOrEmpty(conStr)
						&& (itemStr.contains(conStr) || conStr.contains(itemStr))) {
					score += constraint.getValueStrContains().get(k).getWeight();
				}
			}

		}
		for (String k : constraint.getValueStrLeven().keySet()) {
			if (item.getValueStrLeven().containsKey(k)) {
				String conStr = constraint.getValueStrLeven().get(k).getValue();
				String itemStr = item.getValueStrLeven().get(k).getValue();
				if (!Strings.isNullOrEmpty(itemStr) && !Strings.isNullOrEmpty(conStr)
						&& StringUtils.getLevenshteinDistance(itemStr, itemStr) > leven_threshold) {
					score += constraint.getValueStrLeven().get(k).getWeight();
				}
			}
		}
		return score;
	}

	private List<Record> getConstraintList(int userId) {
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