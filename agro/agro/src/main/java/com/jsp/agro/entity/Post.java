package com.jsp.agro.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "UserPost")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int likes;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Comment> comment;
	
	@OneToOne(cascade = CascadeType.ALL)
	 private Image image;
	
	private String date;
	private String caption;
	private String Location;
	
}
