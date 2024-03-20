package com.jsp.agro.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.jsp.agro.dao.AgroDao;
import com.jsp.agro.entity.User;
import com.jsp.agro.exceptition.DataNotFound;
import com.jsp.agro.exceptition.DataNotUpdate;
import com.jsp.agro.exceptition.EmailNotFound;
import com.jsp.agro.exceptition.PasswordMissmatched;
import com.jsp.agro.exceptition.UserNotFound;
import com.jsp.agro.util.ResponseStructure;

@Service
public class AgroService {
	
	@Autowired
	private AgroDao dao;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	//mailSender
	public void sendSimpleMail(String email, String text, String sub) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("argo0624@gmail.com");
		mailMessage.setTo(email);
		mailMessage.setText(text);
		mailMessage.setSubject(sub);
		javaMailSender.send(mailMessage);
	}
	
	//userRegisterd
	public ResponseEntity< ResponseStructure<User> > register(User user) {
		User save = dao.userRegisterd(user);
		String emailText = "Dear "+user.getFirstName()+ " Congratulations and welcome to Agro! "
				+ "We are thrilled to have you on board, "
				+ "and we appreciate you choosing us for your [service/product] needs.";
		String sub = "Welcome to Agro - Confirm Your Registration by team-6";
		sendSimpleMail(user.getEmail(),emailText,sub);
		ResponseStructure<User> m = new ResponseStructure<User>();
		m.setData(save);
		m.setMsg("User Registration successfully.....");
		m.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.CREATED) ;
	}
	
	//fetchbyId
	public ResponseEntity<ResponseStructure<User>> fetchById(int id) {
		User db = dao.fetchById(id);
		ResponseStructure<User> m = new ResponseStructure<User>();
		if(db!=null) {
			m.setData(db);
			m.setMsg("User Data Fetching successfully.....");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.OK) ;
		}
		else {
			throw new DataNotFound(id+":is not Present");
		}
	}
	
	//fetchByAll
	public  ResponseEntity<ResponseStructure<List<User>>> fetchByAll(){
		 List<User> db = dao.fetchByAll();
		ResponseStructure<List<User>> m = new ResponseStructure<List<User>>();
		if(db!=null) {
			m.setData(db);
			m.setMsg("User Data Fetching successfully.....");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(m,HttpStatus.OK) ;
		}
		throw new DataNotFound();
	}

	//Login
	public ResponseEntity< ResponseStructure<User> >  login(String email, String pwd) {
		String login = dao.fetchByEmail(email, pwd);
		if(login == "true") {
			User data = dao.fetchByEmail(email);
			ResponseStructure<User> m = new ResponseStructure<User>();
			m.setMsg("Login Successfull");
			m.setStatus(HttpStatus.OK.value());
			m.setData(data);
			return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.OK) ;
		}
		else if (login == null) {
			throw new EmailNotFound(email+ " : is not present");
		}else {
			throw new PasswordMissmatched("Password is incorrect"); 
		} 
	}
	//update
	public ResponseEntity< ResponseStructure<User> > updateById( User user) {
		User update = dao.fetchById(user.getId());
		if(update!=null) {
			User db = dao.updateById(user);
			ResponseStructure<User> m = new ResponseStructure<User>();
			m.setData(db);
			m.setMsg(" Data Updated successfully.....");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.OK) ;
		}
		throw new DataNotUpdate(user.getId()+" : is not present") ;
	}
	
	//generated otp
	 public ResponseEntity<ResponseStructure<Integer>> generateOtp(String email) {
		 User emails = dao.fetchByEmail(email);
		 if(emails!=null) {
			 // Generate a random 6-digit OTP
		        Random random = new Random();
		        int otp = 100000 + random.nextInt(900000);
		        ResponseStructure<Integer> otpSend = new ResponseStructure<Integer>();
				otpSend.setData(otp);
				otpSend.setMsg("otp sent successfully");
				otpSend.setStatus(HttpStatus.FOUND.value());
				sendSimpleMail(email, String.valueOf(otp), "Otp Verification");
				return new ResponseEntity<ResponseStructure<Integer>>(otpSend, HttpStatus.FOUND) ;
		 }
			 throw new EmailNotFound(email+" : is not present");
		 
	 }
	 //deleteById
	 public ResponseEntity<ResponseStructure<User>> deleteUserById(int id) {
		User db = dao.deleteUserById(id);
		ResponseStructure<User> m = new ResponseStructure<User>();
		if(db!=null) {
		m.setData(db);
		m.setMsg("Data deleted Successfully");
		m.setStatus(HttpStatus.GONE.value());
		return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.GONE);
		}
		throw new  UserNotFound(id + " : is not present");
	}
	
	
}
