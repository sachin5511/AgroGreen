package com.jsp.agro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.jsp.agro.entity.User;

public interface AgroRepo extends JpaRepository<User, Integer> {
	
	@Query("select a from User a where a.email=?1")
	public abstract User fetchbyEmail(String email);

}
