package com.ymcatpo.app.topapp.controller.Tpo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ymcatpo.app.topapp.Excel.ExcelHelper;
import com.ymcatpo.app.topapp.Excel.ExcelPojo;
import com.ymcatpo.app.topapp.entity.Student.StudentPersonalDetails;
import com.ymcatpo.app.topapp.entity.Tpo.Company;
import com.ymcatpo.app.topapp.serviceInterface.Tpo.CompanyService;




@RestController
@RequestMapping("/ymca/api/company")
public class CompanyController {
	@Autowired
	CompanyService companyService;

	// get list
	@GetMapping("/getCompany")
	public ResponseEntity<List<Company>> getCompanyList() throws Exception{
	 	return new ResponseEntity<>(companyService.CompanyList(), HttpStatus.OK); 
		}
	
	// get single
	@GetMapping("/getCompany/{companyId}")
	public ResponseEntity<Company> getCompanyList(@PathVariable long companyId)throws Exception{
	 	return new ResponseEntity<>( companyService.View(companyId)  ,HttpStatus.OK);
		
	}
	
	// create company
	@PostMapping("/createCompany")
	public ResponseEntity<?> createEntity(@RequestBody Company cmp) throws Exception{
			
		companyService.CreateNew(cmp);
			
		return new ResponseEntity<>(HttpStatus.CREATED);
	} 
	// student applying for the company
	@PutMapping("/addStudent")
	public ResponseEntity<?> updateCompannyStudent(@RequestParam (name = "studentId") String studentId,
													@RequestParam (name = "companyId") long companyId)throws Exception{
			companyService.AppliedCompany(studentId, companyId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	// list the student applied for a particular company
	@GetMapping("/getStudent/{companyId}")
	public ResponseEntity<Set<StudentPersonalDetails>> getstudent(@PathVariable long companyId)throws Exception{
		
		return new ResponseEntity<>(companyService.getPersonalDetails(companyId),HttpStatus.OK);
	}
	// list of the companies in which the student applied
	@GetMapping("/getListCompany/{studentId}")
	public ResponseEntity<Set<Company>> getstudent(@PathVariable String studentId) throws Exception{
		
		return new ResponseEntity<>(companyService.getCompanyList(studentId),HttpStatus.OK);
	}

	@GetMapping("/download/{companyId}")
	 public ResponseEntity<?> excelCustomersReport(@RequestParam String tpoId,@PathVariable  long companyId) throws IOException, MessagingException {
        List<ExcelPojo> customers= companyService.load(companyId);
        ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(customers);    
        companyService.mailCompSender(companyId,in,tpoId);
        return new ResponseEntity<>(HttpStatus.OK);
	}
}
