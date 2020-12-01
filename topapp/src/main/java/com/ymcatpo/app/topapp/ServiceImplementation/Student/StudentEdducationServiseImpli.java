package com.ymcatpo.app.topapp.ServiceImplementation.Student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ymcatpo.app.topapp.Dao.Student.StudentEduccationalDetailsDao;
import com.ymcatpo.app.topapp.Dao.Student.StudentPersonalDetailsDao;
import com.ymcatpo.app.topapp.entity.Student.StudentEducationalDetails;
import com.ymcatpo.app.topapp.entity.Student.StudentPersonalDetails;
import com.ymcatpo.app.topapp.exception.ApiException;
import com.ymcatpo.app.topapp.serviceInterface.Student.StudentEducationalDetailsService;
@Service
public class StudentEdducationServiseImpli implements StudentEducationalDetailsService {
	@Autowired
	private StudentEduccationalDetailsDao stuEduDetail;
	@Autowired
	private StudentPersonalDetailsDao studentPerDao;
	
	
	@Override
	public List<StudentEducationalDetails> getStudentEduDetails()  {
	
			List <StudentEducationalDetails> temp=stuEduDetail.findAll();
			if(temp.isEmpty()) {
				throw new ApiException("Education List is Empty",HttpStatus.NO_CONTENT);
			}
			return temp;
	}

	@Override
	public StudentEducationalDetails getStudent(String rollNo) {
				StudentPersonalDetails stu= studentPerDao.getOne(rollNo);
				StudentEducationalDetails temp= stuEduDetail.findByStu(stu);	
				if(temp== null) {
					throw new ApiException("Education List is Empty", HttpStatus.NO_CONTENT );
				}	
				return temp;
	}
	
	

	@Override
	public StudentEducationalDetails saveStudent(StudentEducationalDetails student, String rollId)  {
	
			Optional<StudentPersonalDetails> stemp= studentPerDao.findById(rollId);
			if(! stemp.isPresent()) {
				throw new ApiException("Roll No don't exist",HttpStatus.BAD_REQUEST);
			}		
			student.setStu(stemp.get());
			stuEduDetail.save(student);
		return stuEduDetail.getOne(student.getId());
	
	}

}
