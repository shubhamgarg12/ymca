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

import com.ymcatpo.app.topapp.entity.Tpo.TpoDetails;
import com.ymcatpo.app.topapp.serviceInterface.Tpo.TpoService;

@RestController
@RequestMapping("/ymca/api/tpo")
public class TpoController {
	
	@Autowired
	TpoService tpo;
	
	@GetMapping("/getTpo/{TpoId}")
	public ResponseEntity<TpoDetails> GetTpo(@PathVariable String tpoId)throws Exception{
				return new ResponseEntity<>(tpo.getTpo(tpoId), HttpStatus.OK);
	}
	
	@PutMapping("/updateTpo/{TpoId}")
	public ResponseEntity<TpoDetails> UpdateTpo(@RequestBody TpoDetails tDetail,@PathVariable String tpoId)throws Exception 
	{
		return new ResponseEntity<>(tpo.putTpo(tDetail, tpoId),HttpStatus.OK);
	}
	
	@PostMapping("/createTpo")
	public ResponseEntity<?> createTpo(@RequestBody TpoDetails tDetails) throws Exception {
		 tpo.createTpo(tDetails);
		 return new ResponseEntity<>(HttpStatus.CREATED);
	}
	


}
