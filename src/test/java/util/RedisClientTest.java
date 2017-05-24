package util;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseControllerTest;
import sspku.util.RedisClient;

public class RedisClientTest extends BaseControllerTest{

	@Autowired
	private RedisClient redisCli;
	
	
	@Test
	public void testRedis(){
		redisCli.set("hello", "world");
		boolean exist=redisCli.existsKey("hello");
		String val=redisCli.get("hello");
		Assert.assertTrue(exist==true);
		Assert.assertTrue(val.equals("world"));
	}

}
