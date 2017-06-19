package sspku.recommendEngine;

import java.util.Map;

public interface IRecAlgorithms {
	Map<String,Double> predict(int userId, int topNum);
}
