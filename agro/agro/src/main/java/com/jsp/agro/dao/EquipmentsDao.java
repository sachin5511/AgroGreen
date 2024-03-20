package com.jsp.agro.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Equipments;
import com.jsp.agro.entity.User;
import com.jsp.agro.repo.EquipmentsRepo;

@Repository
public class EquipmentsDao {
	
	@Autowired
	private EquipmentsRepo repo;
	
	//save
	public Equipments registerd(Equipments equipment) {
		return repo.save(equipment);
	}
	
	//fetchById
	public Equipments fetchById(int id) {
		Optional<Equipments> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}else {
			return null;
		}
	}
	
	//fetch All
	public List<Equipments> fetchByAll() {
		return repo.findAll();
	}
	
	//fetch by user id
	public  Equipments fetchByUid(int uid) {
		List<Equipments> db = repo.findAll();
		for (Iterator iterator = db.iterator(); iterator.hasNext();) {
			Equipments equipments = (Equipments) iterator.next();
			User userdata = equipments.getUser();
			if(userdata.getId() ==uid) {
				return equipments;
			}
		}
		return null; 
	}
	
	//fetch by equipment Name
	public List<Equipments>  fetchByequipmentName(String eqname) {
		return repo.fetchByequipmentName(eqname);
	}

	public Equipments  updateEquipmentData(Equipments equipment) {
		Optional<Equipments> db = repo.findById(equipment.getId());
		if(db.isPresent()) {
			 Equipments data = db.get();
			if(equipment.getEquipName()==null) {
				equipment.setEquipName(data.getEquipName());
			}
			if(equipment.getCostOfEquipement()==0.0) {
				equipment.setCostOfEquipement(data.getCostOfEquipement());
			}
			if(equipment.getQuantity()==0.0) {
				equipment.setQuantity(data.getQuantity());
			}
			if(equipment.getUser()==null) {
				equipment.setUser(data.getUser());
			}
			return repo.save(equipment);
		}
		return null;
	}
	
	//delete
	public Equipments  deleteById(int id) {
		Optional<Equipments> db = repo.findById(id);
		if(db.isEmpty()) {
		return null;
		}else {
			repo.deleteById(id);
			return db.get();
		}
	}
}
