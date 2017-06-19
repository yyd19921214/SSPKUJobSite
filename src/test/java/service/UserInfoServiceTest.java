package service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseControllerTest;
import sspku.dao.UserInfo;
import sspku.dao.UserInfoWithBLOBs;
import sspku.dto.BasicInfo;
import sspku.dto.Expection;
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
	
	@Test
	public void saveExpection(){
		Expection expect=new Expection();
		expect.setExpCity("北京");
		expect.setExpJob("java");
		expect.setExpMinSalary(10);
		expect.setJobType(1);
		
		Expection expect2=new Expection();
		expect2.setExpCity("北京");
		expect2.setExpJob("大数据");
		expect2.setExpMinSalary(15);
		expect2.setJobType(1);
		
		Expection expect3=new Expection();
		expect3.setExpCity("北京");
		expect3.setExpJob("数据挖掘");
		expect3.setExpMinSalary(18);
		expect3.setJobType(1);
		
		List<Expection> expects=new ArrayList<>();
		expects.add(expect);
		expects.add(expect2);
		expects.add(expect3);
		Assert.assertTrue(userService.saveExpection(1, expects));
		
	}
	
	
}
