package com.ymcatpo.app.topapp.controller.Tpo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ymcatpo.app.topapp.entity.user.User;
import com.ymcatpo.app.topapp.model.BasicResponse;
import com.ymcatpo.app.topapp.service.user.UserService;

@RestController
@RequestMapping("/ymca/api/tpo")
public class TpoController {
	
	@Autowired
	UserService tpo;
	
	@GetMapping("/getTpo/{username}")
	public ResponseEntity<User> GetTpo(@PathVariable String username)throws Exception{
				return new ResponseEntity<>(tpo.getTpoById(username), HttpStatus.OK);
	}
	
	@PostMapping("/updateTpo/{username}")
	public ResponseEntity<BasicResponse> UpdateTpo(@RequestBody User tDetail,@PathVariable String username)throws Exception 
	{
		return new ResponseEntity<>(tpo.updateTpo(tDetail,username),HttpStatus.OK);
	}
	
	@PostMapping("/createTpo")
	public ResponseEntity<BasicResponse> createTpo(@RequestBody User tDetails) throws Exception {
		 tpo.registerTPO(tDetails);
		 return new ResponseEntity<>(HttpStatus.CREATED);
	}
	


}
