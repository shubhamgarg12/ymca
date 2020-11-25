package com.ymcatpo.app.topapp.ServiceImplementation.Tpo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ymcatpo.app.topapp.Dao.Student.StudentCertificationDao;
import com.ymcatpo.app.topapp.Dao.Student.StudentEduccationalDetailsDao;
import com.ymcatpo.app.topapp.Dao.Student.StudentPersonalDetailsDao;
import com.ymcatpo.app.topapp.Dao.TPO.CompanyDao;
import com.ymcatpo.app.topapp.Excel.ExcelHelper;
import com.ymcatpo.app.topapp.Excel.ExcelPojo;
import com.ymcatpo.app.topapp.UserDefineException.ApiException;
import com.ymcatpo.app.topapp.entity.Student.StudentCertification;
import com.ymcatpo.app.topapp.entity.Student.StudentEducationalDetails;
import com.ymcatpo.app.topapp.entity.Student.StudentPersonalDetails;
import com.ymcatpo.app.topapp.entity.Tpo.Company;
import com.ymcatpo.app.topapp.serviceInterface.Tpo.CompanyService;

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
	
	
	@Override
	public Company CreateNew(Company cmp) {
		return companyDao.save(cmp);
	}

	@Override
	public void AppliedCompany(String stuId, Long companyId) {
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
		// fire an Confirmation email 
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
			
			if(student.getGender()==0){
					excel.setStudentGender("Male");
				}else {
					excel.setStudentGender("Female");
			}
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
			excel.setStudentEducationYear(education.getYear());
			excel.setStudentEducationGapReason(education.getGapReason());
			List<StudentCertification> certi = studentCertiDao.findByStu(student);
			if(certi.isEmpty()) {
				excel.setStudentcertificationId(null);
				excel.setStudentcertificationOrgiDetails(null);
				excel.setStudentcertificationTitle(null);
				excel.setStudentcertificationIssueDate(null);
				
			}else {
				StringBuffer sb[] = new StringBuffer[4];
				boolean isFirst= true;
			for(StudentCertification certifica :certi) {
				if(! isFirst) {
					sb[0].append(", ");
					sb[1].append(", ");
					sb[2].append(", ");
					sb[3].append(", ");
				}
				sb[0].append(certifica.getCertificateId());
				sb[2].append(certifica.getCertiTitle());
				sb[1].append(certifica.getOrgiDetails());
				sb[3].append(certifica.getIssueDate());
				isFirst= false;			}
			excel.setStudentcertificationId(sb[0].toString());
			excel.setStudentcertificationOrgiDetails(sb[1].toString());
			excel.setStudentcertificationTitle(sb[2].toString());
			excel.setStudentcertificationIssueDate(sb[3].toString());
			
			}
			tutorials.add(excel);
		}
		
	    return tutorials;
		
	}
	
}
