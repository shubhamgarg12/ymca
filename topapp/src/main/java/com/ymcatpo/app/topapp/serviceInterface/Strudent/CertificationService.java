package com.ymcatpo.app.topapp.serviceInterface.Strudent;

import java.util.List;

import com.ymcatpo.app.topapp.entity.Student.StudentCertification;



public interface CertificationService {

	List<StudentCertification> getCertifiactionDetails(String Stuid);
	List<StudentCertification> updateCertiDetail(StudentCertification certi,String Stuid );	
}
