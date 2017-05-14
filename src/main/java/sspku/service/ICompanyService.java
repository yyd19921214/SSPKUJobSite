package sspku.service;

import java.util.List;

import sspku.dao.Company;

public interface ICompanyService {
	Company getCompany(Integer id);

	List<Company> listCompany(int limit);

	List<Company> listCompanyByName(String name);

	int updateCompany(Company company);
}
