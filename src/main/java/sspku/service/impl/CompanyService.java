package sspku.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sspku.dao.Company;
import sspku.mapper.CompanyMapper;
import sspku.service.ICompanyService;
import sspku.util.CompanyConstant;
import sspku.util.LuceneUtil;

@Service
public class CompanyService implements ICompanyService {

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
		try {
			List<Company> companys = LuceneUtil.getSearchCompany(name, LuceneUtil.COMPANY_SIMPLE_NAME).parallelStream()
					.filter(i -> i.score > CompanyConstant.thresholdSearch)
					.map(i -> companyMap.selectBaseByPrimaryKey(Integer.valueOf(i.companyId))).collect(Collectors.toList());
			return companys;
		} catch (NumberFormatException | IOException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateCompany(Company company) {
		// TODO Auto-generated method stub
		return 0;
	}

}
