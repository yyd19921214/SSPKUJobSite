package sspku.recommendEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Strings;

public class ConstraintBasedRecom {

	/** a list contains all items can be recommended to user */
	private List<Record> recomList;// 可推荐的工作列表, 算法自身携带

	private static final double leven_threshold = 0.8;

	public ConstraintBasedRecom(List<Record> jobs) {
		this.recomList = jobs;
	}

	public List<String> predict(List<Record> constraints, int topNum) {
		Map<String, Double> resultMap = new HashMap<>();
		for (Record expect : constraints) {
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
			if (item.getValueStrEq().containsKey(k)
					&& constraint.getValueStrEq().get(k).equals(item.getValueStrEq().get(k).getValue())) {
				score += constraint.getValueStrEq().get(k).getWeight();
			}
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

}