
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

import com.ymcatpo.app.topapp.entity.Student.StudentEducationalDetails;
import com.ymcatpo.app.topapp.serviceInterface.Student.StudentEducationalDetailsService;


@RestController
@RequestMapping("/ymca/api/edu")
public class StudentEducationalDetailsController {

	@Autowired
	StudentEducationalDetailsService education;
	
	@GetMapping("/studentEducation")
	public List<StudentEducationalDetails> getStudent() throws Exception{
		return education.getStudentEduDetails();
	}
	
	// for getting a particular record
	@GetMapping("/studentdetails/education/{rollNo}")
	public ResponseEntity<StudentEducationalDetails> getStudent(@PathVariable String rollNo) throws Exception {
		StudentEducationalDetails edu=	education.getStudent(rollNo);
		return new ResponseEntity<StudentEducationalDetails>(edu, HttpStatus.OK);
	}
	
	// update a specific education Details
	@PostMapping("/StudentEducationalDetails/{rollNo}")
	public ResponseEntity<StudentEducationalDetails> saveStudent(@PathVariable String rollNo,
			@RequestBody StudentEducationalDetails student ) throws Exception  {
		StudentEducationalDetails edu;
		edu = education.saveStudent(student, rollNo);
			return new ResponseEntity<StudentEducationalDetails>(edu, HttpStatus.CREATED);
	
	}
		
	
}
