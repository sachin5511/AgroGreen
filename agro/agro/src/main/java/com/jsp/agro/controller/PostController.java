package com.jsp.agro.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.entity.Post;
import com.jsp.agro.service.PostService;
import com.jsp.agro.util.ResponseStructure;

@RestController
public class PostController {
	
	@Autowired
	private PostService service;
	
	@PostMapping("/userPost")
	public ResponseEntity<ResponseStructure<Post>> save(@ RequestParam int id, @RequestParam ("file") MultipartFile file, @RequestParam String date, @RequestParam String caption, @RequestParam String location ) throws IOException {
		return service.saveUserPost(id,file,date,caption,location);	
	}
	
	@GetMapping("/fetchUserPost")
	public ResponseEntity<ResponseStructure<Post>> fetchById(@RequestParam int id){	
		return service.fetchById(id);
	}
	
	@DeleteMapping("/deleteUserPost")
	public ResponseEntity<ResponseStructure<Post>> deleteById(@RequestParam int id){	
		return service.deleteById(id);
	}
}
