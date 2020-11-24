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
import com.ymcatpo.app.topapp.entity.Student.StudentPersonalDetails;
import com.ymcatpo.app.topapp.serviceInterface.Strudent.StudentPeronalDetailsServices;

@RestController
@RequestMapping("/ymca/api/student")
public class StudentPersonalConroller {

	@Autowired
	private StudentPeronalDetailsServices student;
	
	@GetMapping("/studentdetails")
	public ResponseEntity<List<StudentPersonalDetails>>  getStudent() throws Exception{
		return new ResponseEntity<List<StudentPersonalDetails>>(this.student.getStudentDetails(), HttpStatus.OK);
	}
		
		

	@GetMapping("/studentdetails/{rollNo}")
	public ResponseEntity<StudentPersonalDetails> getStudent(@PathVariable String rollNo) throws Exception{
			return new ResponseEntity<StudentPersonalDetails>(student.getStudent(rollNo),HttpStatus.OK);	
				
	}
	
	@PostMapping("/studentdetails")
	public ResponseEntity<StudentPersonalDetails> saveStudent(@RequestBody StudentPersonalDetails student) throws Exception {
		return new ResponseEntity<StudentPersonalDetails>(this.student.saveStudent(student),HttpStatus.CREATED);
	}
	
	@PutMapping("/studentdetails/{rollNo}")
	public ResponseEntity<StudentPersonalDetails>updateStudent(@RequestBody StudentPersonalDetails student,
																@PathVariable String rollNo) throws Exception {
		return new ResponseEntity<StudentPersonalDetails>(this.student.updateStudent(student,rollNo),HttpStatus.OK);
	}
	
	@DeleteMapping("/studentdetails/{rollNo}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable String rollNo) {
			this.student.deleteStudent(rollNo);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
