package com.ymcatpo.app.topapp.controller.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ymcatpo.app.topapp.entity.Student.StudentCertification;
import com.ymcatpo.app.topapp.serviceInterface.Strudent.CertificationService;

@RestController
@RequestMapping("/ymca/api/certi")
public class StudentCertificationController {
	@Autowired
	private CertificationService certi;
	
	@PostMapping("/certidetail/{rollNo}")
	public ResponseEntity<List<StudentCertification>> updateDetails( @PathVariable String rollNo,
											@RequestBody StudentCertification cetification) throws Exception{
		List<StudentCertification> certifi =certi.updateCertiDetail(cetification, rollNo);
		return new ResponseEntity<>(certifi,HttpStatus.CREATED);
	}
	
	@GetMapping("/certidetails/{rollNo}")
	public ResponseEntity<List<StudentCertification>> getDetails( @PathVariable String rollNo) throws Exception{
		List<StudentCertification> certifi =certi.getCertifiactionDetails(rollNo);
		return new ResponseEntity<>(certifi,HttpStatus.OK);
	}
}
