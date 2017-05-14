package sspku.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sspku.dao.Company;
import sspku.mapper.CompanyMapper;
import sspku.service.ICompanyService;
@Service
public class CompanyService implements ICompanyService{
	
	@Autowired
	private CompanyMapper companyMap;
	
	public void setCompanyMap(CompanyMapper companyMap) {
		this.companyMap = companyMap;
	}


	@Override
	public Company getCompany(Integer id) {
		return companyMap.selectByPrimaryKey(id);
	}

	@Override
	public List<Company> listCompany(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> listCompanyByName(String name) {
		
//		companyMap.selectByExample(example);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateCompany(Company company) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
