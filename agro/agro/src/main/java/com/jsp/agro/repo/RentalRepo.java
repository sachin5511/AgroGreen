package com.jsp.agro.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.agro.entity.Rental;

public interface RentalRepo extends JpaRepository<Rental, Integer> {

}
