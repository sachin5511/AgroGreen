package com.jsp.agro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro.entity.Equipments;
import com.jsp.agro.entity.User;
import com.jsp.agro.service.EquipmentsService;
import com.jsp.agro.util.ResponseStructure;

@RestController
public class EquipmentsController {
	
	@Autowired
	private EquipmentsService equipmentsservice;
	
	//save
	@PostMapping("/equipmentSave")
	public ResponseEntity<ResponseStructure<Equipments>> equipmentsSave( @RequestParam int uid,@RequestBody Equipments equipment ) {
		return equipmentsservice.registerd(uid,equipment);
	}
	
	 //fetch by id
	@GetMapping("/fetchEquipments")
	public ResponseEntity<ResponseStructure<Equipments>> fetchById(@RequestParam int id){	
		return equipmentsservice.fetchById(id);
	}
	
	// fetch By all
	@GetMapping("/fetchAllEquipments")
	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchByAll(){	
		return equipmentsservice.fetchByAll();
	}
	
	//fetching by user id
	@GetMapping("/fetchEquipmentsUid")
	public ResponseEntity<ResponseStructure<Equipments>> fetchByUid(@RequestParam int uid){	
		return equipmentsservice.fetchByUid(uid);
	}
	
	//fetching by EquipmentName
	@GetMapping("/fetchEquipmentsName")
	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchByUid(@RequestParam String eqname){	
		return equipmentsservice.fetchByequipmentName(eqname);
	}
	
	//update data
	@PutMapping("/updateData")
	public ResponseEntity<ResponseStructure<Equipments>> equipmentsUpdate(@RequestBody Equipments equipment ) {
		return equipmentsservice.update(equipment);
	}
	
	//delete
	@DeleteMapping("/deleteEquipmentData")
	public ResponseEntity<ResponseStructure<Equipments>> deleteEquipmentById(@RequestParam int id) {
		return equipmentsservice.deleteById(id);
	}
}
