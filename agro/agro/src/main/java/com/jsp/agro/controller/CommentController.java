package com.jsp.agro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro.entity.Comment;
import com.jsp.agro.service.CommentService;
import com.jsp.agro.util.ResponseStructure;



@RestController
public class CommentController {
	
	@Autowired
	private CommentService service;

	// User save Comment
	@PostMapping("/userComment")
	public ResponseEntity<ResponseStructure<Comment>> saveComment(@RequestParam int pid,@RequestParam int uid,@RequestParam String msgcomment) {
		return service.saveComment(pid,uid,msgcomment);
	}
	
	//delete
	@DeleteMapping("/deletecomment")
	public ResponseEntity<ResponseStructure<Comment>> deleteComment(@RequestParam int commentId){
		return service.deleteComment(commentId);
	}
}
