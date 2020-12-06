package com.ymcatpo.app.topapp.serviceInterface.Tpo;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;

import com.ymcatpo.app.topapp.Excel.ExcelPojo;
import com.ymcatpo.app.topapp.entity.Student.StudentPersonalDetails;
import com.ymcatpo.app.topapp.entity.Tpo.Company;
import com.ymcatpo.app.topapp.model.CompanyModel;

public interface CompanyService {
	 Company CreateNew(Company cmp);
	 void AppliedCompany(String stuId, Long companyId) throws MailException, InterruptedException;
	 List<Company> CompanyList();
	 List<CompanyModel> getCompanyList();
	 Company View(Long companyId);
	Set<StudentPersonalDetails> getPersonalDetails(long companyId);
	Set<Company> getCompanyList(String studentId);
	List<ExcelPojo> load(long companyId);
	boolean mailCompSender(long companyId, ByteArrayInputStream in, String tpoId) throws IOException, MessagingException;

}
