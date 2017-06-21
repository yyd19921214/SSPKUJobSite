package service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseControllerTest;
import sspku.service.impl.UserActionService;

public class UserActionTest extends BaseControllerTest {

	public UserActionTest() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private UserActionService actionSer;

	public void setActionSer(UserActionService actionSer) {
		this.actionSer = actionSer;
	}

	@Test
	public void testCollectJob() {
		boolean b = actionSer.collectJob(2, 12);
		boolean b2 = actionSer.collectJob(2, 17);
		boolean b3 = actionSer.collectJob(2, 19);
		Assert.assertTrue(b);
	}
	
	@Test
	public void  testCollectCompany(){
		boolean b=actionSer.collectCompany(1, 10);
		Assert.assertTrue(b);
		
	}

}
