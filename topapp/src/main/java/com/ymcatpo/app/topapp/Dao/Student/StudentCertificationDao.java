package com.ymcatpo.app.topapp.Dao.Student;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ymcatpo.app.topapp.entity.Student.StudentCertification;
import com.ymcatpo.app.topapp.entity.Student.StudentPersonalDetails;

@Repository
public interface StudentCertificationDao extends JpaRepository<StudentCertification,Long> {

	public List<StudentCertification> findByStu(StudentPersonalDetails stu);
	
}
