package com.jsp.agro.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.User;
import com.jsp.agro.repo.AgroRepo;
import com.jsp.agro.util.ResponseStructure;

@Repository
public class AgroDao {
	
	@Autowired
	private AgroRepo repo;
	
	// User Registration
	public User userRegisterd(User user) {
		return repo.save(user);
	}
	
	//Fetch By id 
	public User fetchById(int id) {
		Optional<User> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		else {
			return null;
		}
	}

	//fetchbyEmail
	public String fetchByEmail(String email, String pwd) {
		 User userEmail = repo.fetchbyEmail(email);
		 if(userEmail!=null) {
				if(userEmail.getPwd().equals(pwd)) {
					return "true";
				}
				else {
					return "false";
				}
		 }
		return null;
	}
	
	//updateData
	public User updateById(User user) {
		 Optional<User> db = repo.findById(user.getId());
		 User data = db.get();
		 if(db.isPresent()) {
			 if(user.getFirstName()==null) {
				 user.setFirstName(data.getFirstName());
			 }
			 if(user.getLastName()==null) {
				 user.setLastName(data.getLastName());
			 }
			 if(user.getEmail()==null) {
				 user.setEmail(data.getEmail());
			 }
			 if(user.getPhone()==0) {
				 user.setPhone(data.getPhone());
			 }
			 if(user.getPwd()==null) {
				 user.setPwd(data.getPwd());
			 }
			 if(user.getAddress()==null) {
				 user.setGender(data.getGender());
			 }
			 if(user.getAddress() == null) {
				 user.setAddress(data.getAddress());
			 }
			 if(user.getImage() == null) {
				 user.setImage(data.getImage());
			 }
			 return repo.save(user);
		 }
		 else {
			 return null;
		 }
	}
	public User fetchByEmail(String email) {
		User emails = repo.fetchbyEmail(email);
		System.out.println(emails);
		if(emails!=null) {
			return emails;
		}
		return null;
	}
	//deleteById
	public User deleteUserById(int id) {
		Optional<User> db = repo.findById(id);
		if(db.isEmpty()) {
		return null;
		}else {
			repo.deleteById(id);
			return db.get();
		}
	}
	//fetchByAll
	public List<User> fetchByAll(){
		return repo.findAll();
	}
}
