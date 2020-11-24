package com.ymcatpo.app.topapp.Dao.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ymcatpo.app.topapp.entity.Student.StudentPersonalDetails;
@Repository
public interface StudentPersonalDetailsDao extends JpaRepository<StudentPersonalDetails, String> {

		
}
