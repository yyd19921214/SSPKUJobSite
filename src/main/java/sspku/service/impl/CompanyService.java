package sspku.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import sspku.dao.Company;
import sspku.mapper.CompanyMapper;
import sspku.service.ICompanyService;
import sspku.util.CompanyConstant;
import sspku.util.LuceneSearchCompany;
import sspku.util.LuceneUtil;
import sspku.util.RedisUtil;

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
		List<LuceneSearchCompany> list = getLuceneCompany(name);
		if (list != null) {
			List<Company> companys = list.parallelStream().filter(i -> i.score > CompanyConstant.thresholdSearch)
					.map(i -> companyMap.selectBaseByPrimaryKey(Integer.valueOf(i.companyId)))
					.collect(Collectors.toList());
			return companys;
		}
		return null;
	}

	@Override
	public int updateCompany(Company company) {
		// TODO Auto-generated method stub
		return 0;
	}

	private List<LuceneSearchCompany> getLuceneCompany(String text) {
		List<LuceneSearchCompany> list = null;
		if (CompanyConstant.USE_CACHE && RedisUtil.existsKey(text)) {
			list = JSONObject.parseArray(RedisUtil.getString(text), LuceneSearchCompany.class);
		} else {
			try {
				list = LuceneUtil.getSearchCompany(text, LuceneUtil.COMPANY_SIMPLE_NAME);
				if (CompanyConstant.USE_CACHE)
					RedisUtil.setString(text, JSON.toJSONString(list));
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
