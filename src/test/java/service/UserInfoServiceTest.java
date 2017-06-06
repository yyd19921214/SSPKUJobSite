package service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseControllerTest;
import sspku.dao.UserInfo;
import sspku.dao.UserInfoWithBLOBs;
import sspku.dto.BasicInfo;
import sspku.mapper.UserInfoMapper;
import sspku.service.impl.UserInfoService;

public class UserInfoServiceTest extends BaseControllerTest {

	@Autowired
	private UserInfoService userService;

	private static UserInfo user;

	@BeforeClass
	public static void setupUser() {
		user = new UserInfoWithBLOBs();
		user.setUsername("yang");
		user.setPhone("12356789");
		user.setEmail("547428014@qq.com");
		user.setPasswd("11223344");
	}

	public void setUserService(UserInfoService userService) {
		this.userService = userService;
	}

	@Test
	public void testRegister() {
		UserInfo u = userService.register(user);
		Assert.assertTrue(u != null);
	}

	@Test
	public void testLogin() {
		Assert.assertTrue(userService.login("yang", "11223344")!=null);
	}
	
	@Test
	public void testSaveBasicInfo(){
		BasicInfo basic=new BasicInfo();
		basic.setCurrentLocation("beijing");
		basic.setGender("male");
		basic.setHighestDegree("bachler");
		basic.setMajor("computer");
		basic.setSchool("whu");
		Assert.assertTrue(userService.saveBasicInfo(1, basic));
	}
}
