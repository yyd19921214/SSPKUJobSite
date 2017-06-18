package sspku.recommendEngine;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public abstract class AbstractModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Train the model.<br>
	 * Some subclass maybe use more than one data file, so here is a array of
	 * dynamic parameters.
	 * 
	 * @param data
	 *            is a array of dynamic parameters, represent different data.
	 *            Remember if you use the parameter data, you need to check its
	 *            length like this:<br>
	 *            if the len of the data parameters is 1, then as follows:<br>
	 *            if(data.length!=1)<br>
	 *            System.err.println(
	 *            "Parameter error: the len of Map<Integer, Set<Integer>> ...data must be one!"
	 *            );
	 */
	@SuppressWarnings("unchecked")
	public abstract void train(Map<Integer, Set<Integer>>... data);

	/**
	 * Predict the ItemScoreList of one user(userID).
	 * <p>
	 * 
	 * @param userID
	 *            is the original userID.
	 * @param topNum
	 *            is a limit number in the top, <0 means no limit.
	 * @param isNew
	 *            whether only recommend new items for this user.
	 * @return
	 */
	public abstract ItemScoreList predict(int userID, int topNum, boolean isNew);

	/**
	 * Predict the score of the userID-itemID, this is for the test.
	 * <p>
	 * 
	 * @param topNum
	 *            is a limit number in the top, <0 means no limit.
	 * @param isNew
	 *            whether only recommend new items for each user.
	 * @return
	 */
	public abstract double predict(int userID, int itemID);

	/**
	 * Load the model object which you have defined before from the file.
	 * <p>
	 * Some subclass maybe use more than one data file, so here is a array of
	 * dynamic parameters.
	 * 
	 * @param modelPath
	 *            is the position of the model file.
	 * @param data
	 *            is a array of dynamic parameters, represent different data. *
	 *            if you want to get new recommendations for user, you need to
	 *            set data.length >= 1 * Remember if you use the parameter data,
	 *            you need to check its length like this:<br>
	 *            if the len of the data parameters is 1, then as follows:<br>
	 *            if(data.length!=1)<br>
	 *            System.err.println(
	 *            "Parameter error: the len of Map<Integer, Set<Integer>> ...data must be one!"
	 *            );
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public abstract void loadModel(String modelPath, Map<Integer, Set<Integer>>... data);

	/**
	 * 
	 * Save the model object into the file.
	 * <p>
	 * 
	 * @param modelPath
	 *            is the position of the model file.
	 */
	public abstract void saveModel(String modelPath);

}
