package com.ymcatpo.app.topapp.controller.Student;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ymcatpo.app.topapp.entity.Student.StudentCertification;
import com.ymcatpo.app.topapp.model.BasicResponse;
import com.ymcatpo.app.topapp.serviceInterface.Student.CertificationService;

@RestController
@RequestMapping("/ymca/api/certi")
public class StudentCertificationController {
	@Autowired
	private CertificationService certi;
	
	@PostMapping("/certidetail/{rollNo}")
	public ResponseEntity<BasicResponse> createDetails( @PathVariable String rollNo,
											@RequestBody StudentCertification cetification) throws Exception{
		certi.createCertiDetail(cetification, rollNo);
		BasicResponse res = new BasicResponse();
		res.setMessage("Success");
		res.setStatus("201");
		return new ResponseEntity<BasicResponse>(res,HttpStatus.CREATED);
	}
	
	@GetMapping("/certidetails/{rollNo}")
	public ResponseEntity<List<StudentCertification>> getDetails( @PathVariable String rollNo) throws Exception{
		List<StudentCertification> certifi =certi.getCertifiactionDetails(rollNo);
		return new ResponseEntity<>(certifi,HttpStatus.OK);
	}
	
	@PutMapping("/certidetails/{rollNo}")
	public ResponseEntity<BasicResponse> updatetDetails( @PathVariable String rollNo,
											 @RequestBody StudentCertification cetification) throws Exception{
		certi.updateCertiDetail(cetification, rollNo);
		BasicResponse res = new BasicResponse();
		res.setMessage("Success");
		res.setStatus("201");
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/certidetails/{certiId}")
	public ResponseEntity<BasicResponse> deleteDetails(@PathVariable("certiId") long certiId){
			certi.deleteDetails(certiId);
			BasicResponse res = new BasicResponse();
			res.setMessage("Success");
			res.setStatus("201");
		return new ResponseEntity<>(res,HttpStatus.OK);
		
	}
	
	
	
}
