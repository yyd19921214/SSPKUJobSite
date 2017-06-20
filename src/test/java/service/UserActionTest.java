package service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseControllerTest;
import sspku.service.impl.ActionService;

public class UserActionTest extends BaseControllerTest {

	public UserActionTest() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private ActionService actionSer;

	public void setActionSer(ActionService actionSer) {
		this.actionSer = actionSer;
	}

	@Test
	public void testCollectJob() {
		boolean b = actionSer.collectJob(1, 12);
		Assert.assertFalse(b);
	}
	
	@Test
	public void  testCollectCompany(){
		boolean b=actionSer.collectCompany(1, 10);
		Assert.assertTrue(b);
		
	}

}
