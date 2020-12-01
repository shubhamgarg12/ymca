package com.ymcatpo.app.topapp.serviceInterface.Student;

import java.util.List;

import com.ymcatpo.app.topapp.entity.Student.StudentEducationalDetails;

public interface StudentEducationalDetailsService {

	List<StudentEducationalDetails> getStudentEduDetails() ;

	StudentEducationalDetails getStudent(String rollNo);

	StudentEducationalDetails saveStudent(StudentEducationalDetails student, String rollId);

}
