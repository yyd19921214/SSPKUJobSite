package util;

import org.junit.Assert;
import org.junit.Test;

import base.BaseControllerTest;
import sspku.util.RedisUtil;

public class RedisClientTest extends BaseControllerTest{


	
	
	@Test
	public void testRedis(){
		RedisUtil.setString("hello", "world");
		boolean exist=RedisUtil.existsKey("hello");
		String val=RedisUtil.getString("hello");
		Assert.assertTrue(exist==true);
		Assert.assertTrue(val.equals("world"));
	}

}
