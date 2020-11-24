package com.ymcatpo.app.topapp.ServiceImplementation.Student;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ymcatpo.app.topapp.Dao.Student.StudentPersonalDetailsDao;
import com.ymcatpo.app.topapp.entity.Student.StudentPersonalDetails;
import com.ymcatpo.app.topapp.serviceInterface.Strudent.StudentPeronalDetailsServices;
import com.ymcatpo.app.topapp.UserDefineException.ApiException;

@Service
public class StudentPersonalDetailsServiceImpli implements StudentPeronalDetailsServices {

	@Autowired
	private StudentPersonalDetailsDao studentDao;
	@Override
	public List<StudentPersonalDetails> getStudentDetails() {
		List<StudentPersonalDetails> student = studentDao.findAll();
		if(student.isEmpty()) {
			throw new ApiException("Student List is empty", HttpStatus.NO_CONTENT);
		}				
		return student;
	}

	@Override
	public StudentPersonalDetails getStudent(String parseLong){
	
		return studentDao.findById(parseLong).orElseThrow(() ->new ApiException("Roll No Is no present",
																	HttpStatus.NO_CONTENT));
		
	}

	@Override
	public StudentPersonalDetails saveStudent(StudentPersonalDetails student) {
		studentDao.save(student);
		return studentDao.getOne(student.getRollNo());
	}

	@Override
	public StudentPersonalDetails updateStudent(StudentPersonalDetails student, String parseLong) {
		studentDao.findById(parseLong).orElseThrow( ()->new ApiException("Roll No Is no present",
													HttpStatus.NO_CONTENT)  );
		studentDao.save(student);
		return studentDao.getOne(student.getRollNo());
	
	}

	@Override
	public void deleteStudent(String parseLong) {
		StudentPersonalDetails stemp= studentDao.findById(parseLong).orElseThrow(() ->
																					new ApiException("Roll No Is no present",
																					HttpStatus.NO_CONTENT));
		studentDao.delete(stemp);
	}


 
}
