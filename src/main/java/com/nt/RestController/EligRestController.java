package com.nt.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nt.Bind.EligBind;
import com.nt.Service.EligService;

@RestController
public class EligRestController {

	@Autowired
	private EligService eligService;
	
	@GetMapping("/elig/{caseNum}")
	public ResponseEntity<EligBind> checkElig(@PathVariable Integer caseNum) {	
		EligBind bind=eligService.checkelig(caseNum);
		return new ResponseEntity<>(bind,HttpStatus.OK);
		
	}
	
	@GetMapping("/genco/{caseNum}")
	public ResponseEntity<String> generateCo(@PathVariable Integer caseNum){
		boolean status=eligService.generateCo(caseNum);
		if(status) {
			return new ResponseEntity<>("Success",HttpStatus.OK);
		}	
		return new ResponseEntity<>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
