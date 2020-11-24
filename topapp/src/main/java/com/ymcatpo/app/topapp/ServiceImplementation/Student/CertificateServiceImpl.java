package com.ymcatpo.app.topapp.ServiceImplementation.Student;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.ymcatpo.app.topapp.Dao.Student.StudentCertificationDao;
import com.ymcatpo.app.topapp.Dao.Student.StudentPersonalDetailsDao;
import com.ymcatpo.app.topapp.UserDefineException.ApiException;
import com.ymcatpo.app.topapp.entity.Student.StudentCertification;
import com.ymcatpo.app.topapp.entity.Student.StudentPersonalDetails;
import com.ymcatpo.app.topapp.serviceInterface.Strudent.CertificationService;

@Service
public class CertificateServiceImpl implements CertificationService {
	@Autowired
	private StudentCertificationDao certiDao;
	@Autowired
	private StudentPersonalDetailsDao stuDao;
	
		
	
	@Override
	public List<StudentCertification> getCertifiactionDetails(String Stuid) {
		StudentPersonalDetails stu = stuDao.findById(Stuid).orElseThrow( () -> new ApiException("Student does not exist",HttpStatus.NO_CONTENT) );
		List<StudentCertification> certifi =certiDao.findByStu(stu);
		if(certifi.size()==0) {
			throw new ApiException("No certification",HttpStatus.NO_CONTENT);
		}
		return(certifi);
	}

	@Override
	public List<StudentCertification> updateCertiDetail(StudentCertification certi, String Stuid) {
		StudentPersonalDetails stu = stuDao.findById(Stuid).orElseThrow( () -> new ApiException("Student doent exist", HttpStatus.NO_CONTENT));
		certi.setStu(stu);
		certiDao.save(certi);	
		List<StudentCertification> certifi =certiDao.findByStu(stu);
		if(certifi.size()==0) {
			throw new ApiException("No certification",HttpStatus.NO_CONTENT);
		}
		return(certifi);
	}

}
