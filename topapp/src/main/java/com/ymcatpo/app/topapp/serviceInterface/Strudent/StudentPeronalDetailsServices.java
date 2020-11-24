package com.ymcatpo.app.topapp.serviceInterface.Strudent;

import java.util.List;

import com.ymcatpo.app.topapp.entity.Student.StudentPersonalDetails;

public interface StudentPeronalDetailsServices {

	List<StudentPersonalDetails> getStudentDetails();

	StudentPersonalDetails getStudent(String parseLong);

	StudentPersonalDetails saveStudent(StudentPersonalDetails student);

	StudentPersonalDetails updateStudent(StudentPersonalDetails student, String parseLong);

	void deleteStudent(String parseLong);

}
