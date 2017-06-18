package sspku.recommendEngine;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Strings;

public class CaseBasedRecom {

	private List<Record> recomList;

	public CaseBasedRecom(List<Record> recomList) {
		this.recomList = recomList;
	}

	public List<String> predict(List<Record> expects, int topNum) {
		Map<String, Double> resultMap = new HashMap<>();
		for (Record expect : expects) {
			for (Record rec : recomList) {
				if (!resultMap.containsKey(rec.getKey())) {
					double score = this.predict(expect, rec);
					resultMap.put(rec.getKey(), score);
				}
			}
		}
		List<String> list = resultMap.entrySet().stream()
				.sorted(Map.Entry.<String, Double> comparingByValue().reversed()).map(i -> i.getKey()).limit(topNum)
				.collect(Collectors.toList());
		return list;
	}

	public double predict(Record expect, Record item) {
		Map<String, DoubleSummaryStatistics> boundaryMap = this.getMaxAndMin(recomList);
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
				if (item.getValueMIB().get(k).getValue() > expect.getValueMIB().get(k).getValue()) {
					score += expect.getValueMIB().get(k).getWeight() * this.simMIB(boundaryMap.get(k).getMax(),
							expect.getValueMIB().get(k).getValue(), item.getValueMIB().get(k).getValue());
				}
			}
		}
		if (expect.getValueLIB() != null) {
			for (String k : expect.getValueLIB().keySet()) {
				if (item.getValueLIB().get(k).getValue() < expect.getValueLIB().get(k).getValue()) {
					score += expect.getValueLIB().get(k).getWeight() * this.simLIB(boundaryMap.get(k).getMin(),
							expect.getValueLIB().get(k).getValue(), item.getValueLIB().get(k).getValue());
				}
			}
		}
		if (expect.getValueStrEq() != null) {
			for (String k : expect.getValueStrEq().keySet()) {
				if (item.getValueStrEq().get(k).getValue() != null && expect.getValueStrEq().get(k).getValue() != null
						&& item.getValueStrEq().get(k).value.equals(expect.getValueStrEq().get(k).value)) {
					score += expect.getValueStrEq().get(k).getWeight();
				}
			}
		}
		if (expect.getValueStrContains() != null) {
			for (String k : expect.getValueStrContains().keySet()) {
				String itemStr = item.getValueStrContains().get(k).getValue();
				String expectStr = expect.getValueStrContains().get(k).getValue();
				if (itemStr != null && expectStr != null
						&& (itemStr.contains(expectStr) || expectStr.contains(itemStr))) {
					score += expect.getValueStrContains().get(k).getWeight();
				}
			}
		}
		if (expect.getValueStrLeven() != null) {
			for (String k : expect.getValueStrLeven().keySet()) {
				String itemStr = item.getValueStrLeven().get(k).getValue();
				String expectStr = expect.getValueStrLeven().get(k).getValue();
				if (!Strings.isNullOrEmpty(itemStr) && !Strings.isNullOrEmpty(expectStr)) {
					score += expect.getValueStrLeven().get(k).getWeight()
							* StringUtils.getLevenshteinDistance(itemStr, expectStr);
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

}
