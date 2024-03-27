package com.jsp.agro.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.agro.entity.PaymentHistory;

public interface PaymentHistoryRepo extends JpaRepository<PaymentHistory, Integer>  {

}
