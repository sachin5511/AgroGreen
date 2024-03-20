package com.jsp.agro.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.agro.entity.Equipments;

public interface EquipmentsRepo extends JpaRepository<Equipments, Integer> {
	@Query("select a from Equipments a where a.equipName = ?1")
	public abstract List<Equipments>fetchByequipmentName(String eqname);

}
