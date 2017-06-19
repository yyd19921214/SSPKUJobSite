package sspku.recommendEngine;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** This is the recommendation result: a list of item-scores for one user ordered by the score in discending.*/
public class ItemScoreList implements Serializable{

	private static final long serialVersionUID = 6L;

	private int userID;
	private List<Integer> items;
	private List<Double> scores;
	/** The score can be kept the first four digits after the decimal point.*/
	private DecimalFormat df = new DecimalFormat("#.0000");
	/**
	 * topNum is a limit number in the top, <0 means no limit.
	 */
	private int topNum;
	
	public ItemScoreList(int userID,int topNum){
		this.userID = userID;
		items = new ArrayList<Integer>();
		scores = new ArrayList<Double>();
		this.topNum = topNum;
	}
	
	public ItemScoreList(int userID,List<Integer> items,List<Double> scores){
		if (items.size()!=scores.size())
			throw new IllegalArgumentException("The len of items is not as same as the len of scores!");
//			System.err.println("The len of items is not as same as the len of scores!");
		this.userID = userID;
		this.items = items;
		this.scores = scores;
		this.topNum = scores.size();
	}
	

	/** 
	 * Find the position to insert
	 * @param srcArray is an array in descending.
	 * @param score is the value which will be inserted into srcArray.
	 * @return
	 */
	private static int binarySearch(List<Double> srcArray, double score){
		int index=Collections.binarySearch(srcArray, score, Collections.reverseOrder());
		return index<0?-index-1:index;
//		return Collections.binarySearch(srcArray, score, Collections.reverseOrder());
	}

	 /** 
	  * Insert a item-score into this class instance.
	  * @param item is a item that may be clicked by this user.
	  * @param score is the pros that this user will click the item.
	  */
	public void insert(Integer item,double score){		
		// check whether it needs to cut
		if (topNum>0 && items.size()==topNum){ // consider whether it needs to cut the list.
			if(scores.get(items.size()-1)>=score) // < the last one, so no need to insert
				return;
			else{ // > the last one, so (item,score) replace the last one
				items.remove(items.size()-1);
				scores.remove(scores.size()-1);
			}
		}
		
		// insert 
		if (items.size()==0){ // first insert
			items.add(item);
			scores.add(score);
		}
		else { // non-first insert
			int i=binarySearch(scores,score);
			items.add(i, item);
			scores.add(i,score);
		}
	}

	/** Get the userID.*/
	public int getUserID(){
		return userID;
	}
	/** Get the items vector.*/
	public List<Integer> getItems(){
		return items;
	}
	/** Get the scores vector.*/
	public List<Double> getScores(){
		return scores;
	}
	
	/**
	 * Change the vetors: items & scores to map.
	 * @return
	 */
	public Map<Integer,Double> toMap(){
		Map<Integer,Double> map = new HashMap<Integer,Double>();
		for(int i=0;i<items.size();i++)
			map.put(items.get(i), scores.get(i));
		return map;
	}
	
	@Override
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append(userID+" ");
		for(int i=0;i<items.size();i++)
			str.append(items.get(i)+" "+df.format(scores.get(i)*100)+" ");
		return str.toString().trim();
	}
	/** Just for test.*/
	public static void main(String[] args) {
		List<Double> list=new ArrayList<>();
		list.add(0,3.2);
//		List<Double> places = Arrays.asList();
//		System.out.println(binarySearch(places, 2.1));
//		System.out.println(binarySearch2(places, 2.1));
//		List<Double> list=new ArrayList<>();
		
		// TODO Auto-generated method stub
//		ItemScoreList isl = new ItemScoreList(110,6);
//		isl.insert(11, .863);
//		isl.insert(12, .833);
//		isl.insert(13, .873);
//		isl.insert(14, .813);
//		isl.insert(15, .843);
//		isl.insert(16, .883);
//		isl.insert(17, .853);
//		System.out.println(isl.toString());
	}

}
