package sspku.recommendEngine;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;

import sspku.dao.UserAction;
import sspku.service.impl.UserActionService;
import sspku.util.DateUtil;
import sspku.util.RedisUtil;
import sspku.util.UserActionConstant;

public class CollabrativeBasedRecom implements IRecAlgorithms {
	
	private static String REDIS_KEY="CollabrativeKey"; 

	@Autowired
	private UserActionService actSer;
	@Autowired
	private ExtractCandidateJobUtil extractUtil;

	public CollabrativeBasedRecom() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, Double> predict(int userId, int topNum) {
		// TODO Auto-generated method stub
		return null;
	}

	public void calculateSimilarity() {
		CollabrativeOfflineHelper offlineHelper = new CollabrativeOfflineHelper();
		List<UserAction> postJob = actSer.getAllJobs(DateUtil.getdate(-100), UserActionConstant.POST_JOB);
		if (CollectionUtils.isNotEmpty(postJob)) {
			Map<Integer, Long> countMap = postJob.stream()
					.collect(Collectors.groupingBy(p -> p.getJobid(), Collectors.counting()));
			Map<Integer, Set<Integer>> userMap = postJob.stream().collect(Collectors.groupingBy(p -> p.getJobid(),
					Collectors.mapping(p -> p.getUserid(), Collectors.toSet())));
			for (Integer k1 : countMap.keySet()) {
				for (Integer k2 : countMap.keySet()) {
					if (k1 == k2)
						continue;
					int commonPostNum = Sets.intersection(userMap.get(k1), userMap.get(k2)).size();
					double score = commonPostNum / Math.sqrt(countMap.get(k1) * countMap.get(k2));
					offlineHelper.addPairPostJob(k1, k2, score);
				}
			}
		}
		List<UserAction> collectJob = actSer.getAllJobs(DateUtil.getdate(-100), UserActionConstant.COLLECT_JOB);
		if (CollectionUtils.isNotEmpty(collectJob)) {
			Map<Integer, Long> countMap = collectJob.stream()
					.collect(Collectors.groupingBy(c -> c.getJobid(), Collectors.counting()));
			Map<Integer, Set<Integer>> userMap = collectJob.stream().collect(Collectors.groupingBy(c -> c.getJobid(),
					Collectors.mapping(c -> c.getUserid(), Collectors.toSet())));
			for (Integer k1 : countMap.keySet()) {
				for (Integer k2 : countMap.keySet()) {
					if (k1 == k2)
						continue;
					int commonPostNum = Sets.intersection(userMap.get(k1), userMap.get(k2)).size();
					double score = commonPostNum / Math.sqrt(countMap.get(k1) * countMap.get(k2));
					offlineHelper.addPairCollectJob(k1, k2, score);
				}
			}
		}
		offlineHelper.calculateWeightedScore();
		RedisUtil.setString(REDIS_KEY, JSON.toJSONString(offlineHelper));
	}

	public static void main(String[] args) {
		List<String> l = null;
		System.out.println(CollectionUtils.isNotEmpty(l));
	}

}
