package sspku.util;

public class LuceneSearchCompany {
	
	public String companyId;
	public String name;
	public String alias;
	public double score;
	
	public LuceneSearchCompany(String companyId, String name, String alias,double score) {
		super();
		this.companyId = companyId;
		this.name = name;
		this.alias = alias;
		this.score=score;
	}

	public LuceneSearchCompany() {
		super();
	}
	
	
	



}
