package com.jsp.agro.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Rental;
import com.jsp.agro.repo.RentalRepo;

@Repository
public class RentalDao {
	
	@Autowired
	private RentalRepo repo;
	
	//save
	public Rental rentalSave(Rental rental) {
		return repo.save(rental);
	}
}
