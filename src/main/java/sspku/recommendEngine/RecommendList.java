package sspku.recommendEngine;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.base.Strings;

public class RecommendList {

	private String userId;
	private List<Record> items;
	private int topNum;

	private static DecimalFormat df = new DecimalFormat("#.0000");

	public RecommendList(String userId, List<Record> items) {
		if (Strings.isNullOrEmpty(userId)) {
			throw new IllegalArgumentException("the userId an not be empty");
		}
		this.userId = userId;
		this.items = items;
		this.topNum = items.size();
	}

	public RecommendList(String userId, int topNum) {
		if (Strings.isNullOrEmpty(userId)) {
			throw new IllegalArgumentException("the userId can not be empty");
		}
		if (topNum <= 0) {
			throw new IllegalArgumentException("the topNum can not less than zero");
		}
		this.userId = userId;
		this.items = new ArrayList<>(topNum);
		this.topNum = topNum;
	}

	private int binarySearch(Record item) {
		List<Double> scoreArray = items.stream().map(i -> i.getScore()).collect(Collectors.toList());
		int index = Collections.binarySearch(scoreArray, item.getScore(), Collections.reverseOrder());
		return index < 0 ? -index - 1 : index;
	}

	public void insert(Record item) {
		if (topNum > 0 && items.size() == topNum) {
			if (items.get(items.size() - 1).getScore() >= item.getScore())
				return;
			else {
				items.remove(items.size() - 1);
			}
		}
		if (items.size() == 0) {
			items.add(item);
		} else {
			int i = binarySearch(item);
			items.add(i, item);
		}
	}

	public Map<String, Double> toMap() {
		Map<String, Double> map = new HashMap<String, Double>();
		this.items.stream().forEach(i -> {
			map.put(i.getKey(), i.getScore());
		});
		return map;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(userId + " ");
		str.append(
				String.join(" ", items.stream().map(i -> df.format(i.getScore() * 100)).collect(Collectors.toList())));
		return str.toString().trim();
	}

	public String getUserId() {
		return userId;
	}

	public List<Record> getItems() {
		return items;
	}

	public int getTopNum() {
		return topNum;
	}

}
