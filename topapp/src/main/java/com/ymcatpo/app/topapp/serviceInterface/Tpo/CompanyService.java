package com.ymcatpo.app.topapp.serviceInterface.Tpo;

import java.io.InputStream;
import java.util.List;

import com.ymcatpo.app.topapp.entity.Student.StudentPersonalDetails;
import com.ymcatpo.app.topapp.entity.Tpo.Company;

public interface CompanyService {
	 Company CreateNew(Company cmp);
	 void AppliedCompany(String stuId, Long companyId);
	 List<Company> CompanyList();
	 Company View(Long companyId);
	List<StudentPersonalDetails> getPersonalDetails(long companyId);
	List<Company> getCompanyList(String studentId);
	InputStream load(long companyId);
}
