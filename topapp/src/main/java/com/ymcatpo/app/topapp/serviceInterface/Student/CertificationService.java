package com.ymcatpo.app.topapp.serviceInterface.Student;

import java.util.List;

import com.ymcatpo.app.topapp.entity.Student.StudentCertification;



public interface CertificationService {

	List<StudentCertification> getCertifiactionDetails(String Stuid);
	void createCertiDetail(StudentCertification certi,String Stuid );
	void updateCertiDetail(StudentCertification certi,String Stuid);
	void deleteDetails (long certiId);
}
