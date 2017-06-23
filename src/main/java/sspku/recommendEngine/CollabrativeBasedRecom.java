package sspku.recommendEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import sspku.dao.UserAction;
import sspku.service.impl.UserActionService;
import sspku.util.DateUtil;
import sspku.util.RedisUtil;
import sspku.util.UserActionConstant;

@Service
public class CollabrativeBasedRecom implements IRecAlgorithms {

	private static String REDIS_KEY = "CollabrativeKey";

	@Autowired
	private UserActionService actSer;
	@Autowired
	private ExtractCandidateJobUtil extractUtil;

	public CollabrativeBasedRecom() {
		// TODO Auto-generated constructor stub
	}

	public void setExtractUtil(ExtractCandidateJobUtil extractUtil) {
		this.extractUtil = extractUtil;
	}

	@Override
	public Map<String, Double> predict(int userId, int topNum) {
		Map<String, Double> result = new HashMap<>();
		Multimap<String, Double> myMultimap = ArrayListMultimap.create();
		CollabrativeOfflineHelper offlineHelper = JSON.parseObject(RedisUtil.getString(REDIS_KEY),
				CollabrativeOfflineHelper.class);
		List<Integer> candidates = extractUtil.getCandidatedJob(userId).parallelStream().map(i -> i.getId())
				.collect(Collectors.toList());
		List<UserAction> post = actSer.getPostedJobs(userId, DateUtil.getdate(-7));
		List<UserAction> collect = actSer.getCollectorJobs(userId, DateUtil.getdate(-7));
		post.addAll(collect);
		Set<Integer> historyItemSet = post.parallelStream().map(i -> i.getJobid()).distinct()
				.collect(Collectors.toSet());
		for (Integer job1Id : historyItemSet) {
			for (Integer job2Id : candidates) {
				if(historyItemSet.contains(job2Id)){
					continue;
				}
				String key = job1Id <= job2Id ? String.format(CollabrativeOfflineHelper.KEY_FORMAT, job1Id, job2Id)
						: String.format(CollabrativeOfflineHelper.KEY_FORMAT, job2Id, job1Id);
				if ( offlineHelper.getWeightedSimilarity().containsKey(key)) {
					myMultimap.put(String.valueOf(job2Id), offlineHelper.getWeightedSimilarity().get(key));
				}
			}
		}
		myMultimap.keySet().forEach(k -> {
			result.put(k, myMultimap.get(k).stream().reduce(Double::max).orElse(0.0));
		});
		return result.entrySet().stream().sorted(Map.Entry.<String, Double> comparingByValue().reversed()).limit(topNum)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	public boolean calculateSimilarity() {
		// TODO can fasten by multithread
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
		return true;
	}

	public boolean calculateSimilarityMultiThread() throws InterruptedException {
		CollabrativeOfflineHelper offlineHelper = new CollabrativeOfflineHelper();
		ExecutorService service = Executors.newFixedThreadPool(2);
		service.submit(() -> {
			List<UserAction> postJob = actSer.getAllJobs(DateUtil.getdate(-100), UserActionConstant.POST_JOB);
			if (CollectionUtils.isNotEmpty(postJob)) {
				Map<Integer, Long> countMap = postJob.parallelStream()
						.collect(Collectors.groupingBy(p -> p.getJobid(), Collectors.counting()));
				Map<Integer, Set<Integer>> userMap = postJob.parallelStream().collect(Collectors
						.groupingBy(p -> p.getJobid(), Collectors.mapping(p -> p.getUserid(), Collectors.toSet())));
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
		});
		service.submit(() -> {
			List<UserAction> collectJob = actSer.getAllJobs(DateUtil.getdate(-100), UserActionConstant.COLLECT_JOB);
			if (CollectionUtils.isNotEmpty(collectJob)) {
				Map<Integer, Long> countMap = collectJob.parallelStream()
						.collect(Collectors.groupingBy(c -> c.getJobid(), Collectors.counting()));
				Map<Integer, Set<Integer>> userMap = collectJob.parallelStream().collect(Collectors
						.groupingBy(c -> c.getJobid(), Collectors.mapping(c -> c.getUserid(), Collectors.toSet())));
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
		});
		service.shutdown();
		service.awaitTermination(1000, TimeUnit.SECONDS);
		offlineHelper.calculateWeightedScore();
		RedisUtil.setString(REDIS_KEY, JSON.toJSONString(offlineHelper));
		return true;
	}

}
