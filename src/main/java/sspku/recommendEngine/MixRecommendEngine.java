package sspku.recommendEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

@Service
public class MixRecommendEngine {

	@Autowired
	List<IRecAlgorithms> recommendAlgorithms;

	public Map<String, Double> getMixedRecommend(int userId, int topNum) {
		Map<String, Double> result = new HashMap<>();
		for (IRecAlgorithms algo : recommendAlgorithms) {
			Map<String, Double> predictResult = algo.predict(userId, topNum);
			normalize(predictResult);
			predictResult.entrySet().parallelStream().forEach(e -> {
				result.put(e.getKey(), Math.max(result.getOrDefault(e.getKey(), 0.0), e.getValue()));
			});
		}
		return result.entrySet().stream().sorted(Map.Entry.<String, Double> comparingByValue().reversed()).limit(topNum)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	// nor(x1)=x1/x1+x2+x3....
	private void normalize(Map<String, Double> predictResult) {
		double sum = predictResult.entrySet().parallelStream().map(e -> e.getValue()).reduce(0.0, Double::sum);
		predictResult.keySet().forEach(k -> {
			predictResult.put(k, predictResult.get(k) / sum);
		});
	}

	public static void main(String[] args) {
		Map<String, Double> predictResult = Maps.newHashMap(ImmutableMap.of("a", 1.0, "b", 2.0, "c", 4.0, "d", 5.0));
		// normalize(predictResult);
		System.out.println(predictResult);

	}
}
