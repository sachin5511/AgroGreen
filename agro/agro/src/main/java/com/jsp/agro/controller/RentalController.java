package com.jsp.agro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jsp.agro.entity.Rental;
import com.jsp.agro.service.RentalService;
import com.jsp.agro.util.ResponseStructure;

@RestController
public class RentalController {
	
	@Autowired
	private RentalService rentalservice;
	
	//save
	@PostMapping("/rentalsave")
	public ResponseEntity<ResponseStructure<Rental>> rentalSave(@RequestParam int equipment_uid, @RequestBody Rental rental){
		return rentalservice.rentalSave(equipment_uid,rental);
		
	}

}
