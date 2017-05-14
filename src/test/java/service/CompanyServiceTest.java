package service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseControllerTest;
import sspku.dao.Company;
import sspku.service.impl.CompanyService;


public class CompanyServiceTest extends BaseControllerTest{
	
	@Autowired
	private CompanyService service;

	public CompanyServiceTest() {
		// TODO Auto-generated constructor stub
	}
	
	

    public void setService(CompanyService service) {
		this.service = service;
	}



	@Test
    public void testGetCompanyById(){
    	Integer id=1;
    	Company company=service.getCompany(id);
    	Assert.assertNotNull(company);
    }
}
