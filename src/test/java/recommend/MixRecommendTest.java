package recommend;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseControllerTest;
import sspku.recommendEngine.MixRecommendEngine;

public class MixRecommendTest extends BaseControllerTest{
	
	@Autowired
	private MixRecommendEngine engine;
	
	@Test
	public void testGetRecommenResult(){
		Map<String,Double> result=engine.getMixedRecommend(1, 20);
		Assert.assertTrue(result.size()>0);
		System.out.println(result);
	}
}
