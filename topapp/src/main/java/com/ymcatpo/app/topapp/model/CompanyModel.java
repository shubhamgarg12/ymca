package com.ymcatpo.app.topapp.model;

import com.ymcatpo.app.topapp.entity.Tpo.Company;

public class CompanyModel {

	private Company company;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public CompanyModel(Company company, int countStudent) {

		this.company = company;
		this.countStudent = countStudent;
	}

	private int countStudent;

	public int getCountStudent() {
		return countStudent;
	}

	public void setCountStudent(int countStudent) {
		this.countStudent = countStudent;
	}

}
