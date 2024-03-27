package com.jsp.agro.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.PaymentHistory;
import com.jsp.agro.repo.PaymentHistoryRepo;

@Repository
public class PaymentHistoryDao {
	
	@Autowired
	private PaymentHistoryRepo repo;

	// User Registration
	public PaymentHistory savePayment(PaymentHistory payment) {
		return repo.save(payment);
	}
}
