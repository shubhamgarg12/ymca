package com.ymcatpo.app.topapp.ServiceImplementation.Tpo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.ymcatpo.app.topapp.Dao.Student.StudentCertificationDao;
import com.ymcatpo.app.topapp.Dao.Student.StudentEduccationalDetailsDao;
import com.ymcatpo.app.topapp.Dao.Student.StudentPersonalDetailsDao;
import com.ymcatpo.app.topapp.Dao.TPO.CompanyDao;
import com.ymcatpo.app.topapp.Excel.ExcelHelper;
import com.ymcatpo.app.topapp.Excel.ExcelPojo;
import com.ymcatpo.app.topapp.MailService.MailerService;
import com.ymcatpo.app.topapp.entity.Student.StudentCertification;
import com.ymcatpo.app.topapp.entity.Student.StudentEducationalDetails;
import com.ymcatpo.app.topapp.entity.Student.StudentPersonalDetails;
import com.ymcatpo.app.topapp.entity.Tpo.Company;
import com.ymcatpo.app.topapp.entity.Tpo.TpoDetails;
import com.ymcatpo.app.topapp.exception.ApiException;
import com.ymcatpo.app.topapp.serviceInterface.Tpo.CompanyService;
import com.ymcatpo.app.topapp.serviceInterface.Tpo.TpoService;

@Service
public class CompanyServiceImpl implements CompanyService  {

	@Autowired
	private CompanyDao companyDao;
	@Autowired 
	private StudentPersonalDetailsDao studentDao;
	@Autowired
	private StudentEduccationalDetailsDao studentEduDao;
	@Autowired
	private StudentCertificationDao studentCertiDao;
	@Autowired
	private MailerService mailService;
	@Autowired
	private TpoService tpoSer;
	@Override
	public Company CreateNew(Company cmp) {
		try { 
		Company company=companyDao.save(cmp);
		 	return companyDao.getOne(company.getCompanyId());
		}catch(Exception e) {
			throw new ApiException("Error  while creating company", HttpStatus.CONFLICT);
		}
		 	
	}

	@Override
	public void AppliedCompany(String stuId, Long companyId) throws MailException, InterruptedException {
			Optional<StudentPersonalDetails> stu=	studentDao.findById(stuId);
		Optional<Company>	comp =companyDao.findById(companyId);
		
		if(! comp.isPresent()){
			throw new ApiException("Company does not exist", HttpStatus.NO_CONTENT);
		} 
		if(! stu.isPresent()) {
			throw new ApiException("RollNo does not exist", HttpStatus.NO_CONTENT);
		}
		Set<StudentPersonalDetails> temp = comp.get().getStudent();
		temp.add(stu.get());
		comp.get().setStudent(temp);
		Set<Company> temp2 = stu.get().getCompany_id();
		temp2.add(comp.get());
		stu.get().setCompany_id(temp2);
		companyDao.save(comp.get());
		studentDao.save(stu.get());
		mailService.sendNotificaitoin(stu.get().getEmail(), 4, comp.get().getCompanyName());
	}

	@Override
	public List<Company> CompanyList() {
		List<Company> list =	companyDao.findAll();
			if(list.isEmpty()) {
				throw  new ApiException("Company List is empty", HttpStatus.NO_CONTENT);
			}
		return list;
	}

	@Override
	public Company View(Long companyId) {
		Optional<Company> company= 	companyDao.findById(companyId);
		if(!company.isPresent()) {
			throw  new ApiException("Wrong Company Id", HttpStatus.NO_CONTENT);
		}
		return company.get();
	}

	@Override
	public Set<StudentPersonalDetails> getPersonalDetails(long companyId) {
		 Company  cmp= companyDao.findById(companyId).orElseThrow( () -> new ApiException("No company exist for given Id",
																					HttpStatus.NO_CONTENT) );
		 Set<StudentPersonalDetails> list= cmp.getStudent();
		if(list.isEmpty()) {
			new ApiException("No Student have registered yet",
					HttpStatus.NO_CONTENT);
		}
		
		return list;
	}

	@Override
	public Set<Company> getCompanyList(String studentId) {
		
	StudentPersonalDetails stu=	studentDao.findById(studentId).orElseThrow( () -> new ApiException("Student RollNo doesnt exist",
				HttpStatus.NO_CONTENT) );
	Set<Company> list = stu.getCompany_id();
		if(list.isEmpty()) {
			new ApiException("student havent apply in any company yet",
					HttpStatus.NO_CONTENT);
		}
		return list;
	}

	@Override
	public List<ExcelPojo> load(long companyId) {
		
		Set<StudentPersonalDetails> list= getPersonalDetails(companyId);
		List<ExcelPojo> tutorials = new ArrayList<ExcelPojo>();
		for(StudentPersonalDetails student :list) {
			ExcelPojo excel = new ExcelPojo();
			excel.setStudentRollNo(student.getRollNo());
			excel.setStudentFullName(student.getFullName());
			excel.setStudentFatherName(student.getFatherName());
			excel.setStudentMotherName(student.getMotherName());
			excel.setStudentGender(student.getGender());
			excel.setStudentEmail(student.getEmail());
			excel.setStudentContactNo(student.getContactNo());
			excel.setStudentAddress(student.getAddress());
			excel.setStudentZipCode(student.getZipCode());
			excel.setStudentState(student.getState());
			excel.setStudentCcity(student.getCity());
			StudentEducationalDetails education = studentEduDao.findByStu(student);
			excel.setStudentEducationId(education.getId());
			excel.setStudentEducationSscMarks(education.getSscMarks());
			excel.setStudentEducationSscPassYear(education.getSscPassYear());
			excel.setStudentEducationHsscMarks(education.getHsscMarks());
			excel.setStudentEducationHsscStream(education.getHsscStream());
			excel.setStudentEducationHsscPasYear(education.getHsscPasYear());
			excel.setStudentEducationGradCourse(education.getGradCourse());
			excel.setStudentEducationGradsCgpa(education.getGradsCgpa());
			excel.setStudentEducationGradPassYear(education.getGradPassYear());
			excel.setStudentEducationPgCourse(education.getPgCourse());
			excel.setStudentEducationPgCgpa(education.getPgCgpa());
			excel.setStudentEducationPgPassYear(education.getPgPassYear());
			excel.setStudentEducationYear(education.getGapYear());
			excel.setStudentEducationGapReason(education.getGapReason());
			List<StudentCertification> certi = studentCertiDao.findByStu(student);
			if(certi.isEmpty()) {
				excel.setStudentcertificationId(null);
				excel.setStudentcertificationOrgiDetails(null);
				excel.setStudentcertificationTitle(null);
				excel.setStudentcertificationIssueDate(null);
				
			}else {
				StringJoiner sb1 = new StringJoiner(", ");
				StringJoiner sb2 = new StringJoiner(", ");
				StringJoiner sb3 = new StringJoiner(", ");
				StringJoiner sb4 = new StringJoiner(", ");
				
			for(StudentCertification certifica :certi) {
				sb1.add((CharSequence) (String.valueOf(certifica.getCertificateId())));
				sb2.add((CharSequence) (certifica.getOrgiDetails()));
				sb3.add((CharSequence) (certifica.getCertiTitle() ));
				sb4.add((CharSequence) (certifica.getIssueDate()));
			}
			excel.setStudentcertificationId(sb1.toString());
			excel.setStudentcertificationOrgiDetails(sb2.toString());
			excel.setStudentcertificationTitle(sb3.toString());
			excel.setStudentcertificationIssueDate(sb4.toString());
			
			}
			tutorials.add(excel);
		}
		
	    return tutorials;
		
	}

	@Override
	public boolean mailCompSender(long companyId, ByteArrayInputStream in, String tpoId) throws IOException, MessagingException {
		TpoDetails tpo = tpoSer.getTpo(tpoId);
		mailService.sendattachment(Long.toString(companyId), in, tpo.getTpoEmail());
		return false;
	}
	
}
