package com.jsp.agro.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro.dao.EquipmentsDao;
import com.jsp.agro.dao.PaymentHistoryDao;
import com.jsp.agro.dao.RentalDao;
import com.jsp.agro.entity.Equipments;
import com.jsp.agro.entity.PaymentHistory;
import com.jsp.agro.entity.Rental;
import com.jsp.agro.exceptition.DateInvalid;
import com.jsp.agro.exceptition.UserNotFound;
import com.jsp.agro.util.ResponseStructure;

@Service
public class RentalService {
	
	@Autowired
	private RentalDao dao;
	
	@Autowired
	private EquipmentsDao equipmentDao;
	
	@Autowired
	private PaymentHistoryDao paymentDao;
	
	//save
	public ResponseEntity<ResponseStructure<Rental>> rentalSave(int equipment_uid, Rental rental)  {
		Equipments equipmentData = equipmentDao.fetchById(equipment_uid);
		if(equipmentData!=null) {
			int starthr = rental.getStartTime().getHours();
			int endhr = rental.getEndTime().getHours();
			double price = equipmentData.getCostOfEquipement();
			PaymentHistory paymentHistory = new PaymentHistory();
			double amountPrice =0;
			if(starthr<=endhr) {
				int minutConvert = (endhr-starthr)*60;
				amountPrice = amount(minutConvert,rental,price);	 
			}else {
				if((12-starthr)>=0) {
					int minutConvert = ((12-starthr)+endhr)*60;
					amountPrice = amount(minutConvert,rental,price);
				}
				throw new DateInvalid("Date invalid");
			}
			paymentHistory.setAmount(amountPrice);	
			paymentDao.savePayment(paymentHistory);
			rental.setEquipment(equipmentData);
			rental.setPaymentHistory(paymentHistory);
			Rental rentalData = dao.rentalSave(rental);
			ResponseStructure<Rental> m = new ResponseStructure<Rental>();
			m.setData(rentalData);
			m.setMsg("Equipments done.....");
			m.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Rental>>(m,HttpStatus.CREATED) ;
		}
		throw new UserNotFound(equipment_uid+" : id is not present ");
	}
	public double amount(int minutConvert,Rental rental,double price) {
		int startMm = rental.getStartTime().getMinutes();
		int endMm = rental.getEndTime().getMinutes();
		if(startMm<=endMm) {
			double minutConvert2 = (endMm-startMm)+minutConvert;
			 return (price/60)*minutConvert2;
		}else {
			double minutConvert2 =minutConvert- (startMm-endMm);
			return (price/60)*minutConvert2;
		}
		
	}

}
