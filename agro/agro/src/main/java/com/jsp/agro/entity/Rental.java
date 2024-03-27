package com.jsp.agro.entity;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Date startTime;
	private Date  endTime;
	
	@ManyToOne
	private Equipments equipment;
	
	@OneToOne
	private PaymentHistory paymentHistory;
	 
	 

}
