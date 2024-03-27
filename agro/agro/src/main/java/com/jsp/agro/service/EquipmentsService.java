package com.jsp.agro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro.dao.AgroDao;
import com.jsp.agro.dao.EquipmentsDao;
import com.jsp.agro.entity.Equipments;
import com.jsp.agro.entity.User;
import com.jsp.agro.exceptition.DataNotFound;
import com.jsp.agro.exceptition.DataNotUpdate;
import com.jsp.agro.exceptition.UserNotFound;
import com.jsp.agro.util.ResponseStructure;

import jakarta.persistence.Id;

@Service
public class EquipmentsService {
	
	@Autowired
	private EquipmentsDao dao;
	
	@Autowired
	private AgroDao agrodao; 
	
	//save
	public ResponseEntity<ResponseStructure<Equipments>> registerd( int uid,Equipments equipment) {
		User userdata = agrodao.fetchById(uid);
		if(userdata!=null) {
			equipment.setUser(userdata);
			Equipments equipmentdata = dao.registerd(equipment);
			ResponseStructure<Equipments> m = new ResponseStructure<Equipments>();
			m.setData(equipmentdata);
			m.setMsg("Equipments done.....");
			m.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Equipments>>(m,HttpStatus.CREATED) ;
		}
		throw new UserNotFound(uid+" : is not present");
	}
	
	//fetch by id
	public ResponseEntity<ResponseStructure<Equipments>> fetchById(int id) {
		Equipments db = dao.fetchById(id);
		ResponseStructure<Equipments> m = new ResponseStructure<Equipments>();
		if(db!=null) {
			m.setData(db);
			m.setMsg("Equipments Data Fetching successfully.....");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Equipments>>(m,HttpStatus.OK) ;
		}
		else {
			throw new DataNotFound(id+":is not Present");
		}
	}
	
	//fetch by all
	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchByAll() {
		List<Equipments> db = dao.fetchByAll();
		ResponseStructure<List<Equipments>> m = new ResponseStructure<List<Equipments>>();
		if(db!=null) {
			m.setData(db);
			m.setMsg("Data Fetching successfully.....");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Equipments>>>(m,HttpStatus.OK) ;
		}
		throw new DataNotFound();
	}
	
	//fetch by user id
	public ResponseEntity<ResponseStructure<Equipments>> fetchByUid(int uid) {
		  Equipments db = dao.fetchByUid(uid);
		 ResponseStructure<Equipments> m = new ResponseStructure<Equipments>();
		 if(db!=null) {
			 m.setData(db);
			 m.setMsg("Data Fetching successfully.....");
			 m.setStatus(HttpStatus.OK.value());
			 return new ResponseEntity<ResponseStructure<Equipments>>(m,HttpStatus.OK) ;
		 }
		 throw new UserNotFound(uid+" : id is not present");
	}
	
	//fetch by equipmentName
	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchByequipmentName(String eqname) {
		List<Equipments> db = dao.fetchByequipmentName(eqname);
		ResponseStructure<List<Equipments>> m = new ResponseStructure<List<Equipments>>();
		if(db!=null) {
			m.setData(db);
			m.setMsg("Data Fetching successfully.....");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Equipments>>>(m,HttpStatus.OK) ;
		}
		throw new DataNotFound("Data no found");
	}
	
	//update
	public ResponseEntity<ResponseStructure<Equipments>> update( Equipments equipment) {
		 Equipments equipdata = dao.fetchById(equipment.getId());
		 if(equipdata!=null) {
			Equipments update = dao.updateEquipmentData(equipment);
			ResponseStructure<Equipments> m = new ResponseStructure<Equipments>();
			m.setData(update);
			m.setMsg(" Data Updated successfully.....");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Equipments>>(m,HttpStatus.OK) ;
		}
		throw new DataNotUpdate(equipment.getId()+" : is not present") ;
	}
	
	//delete
	public ResponseEntity<ResponseStructure<Equipments>> deleteById(int id) {
		Equipments db = dao.deleteById(id);
		ResponseStructure<Equipments> m = new ResponseStructure<Equipments>();
		if(db!=null) {
		m.setData(db);
		m.setMsg("delete Successfully......");
		m.setStatus(HttpStatus.GONE.value());
		return new ResponseEntity<ResponseStructure<Equipments>>(m,HttpStatus.GONE);
		}
		throw new  UserNotFound("id "+id + " : is not present");
	}
}
