package com.jsp.agro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro.dao.AgroDao;
import com.jsp.agro.dao.CommentDao;
import com.jsp.agro.dao.PostDao;
import com.jsp.agro.entity.Comment;
import com.jsp.agro.entity.Post;
import com.jsp.agro.entity.User;
import com.jsp.agro.exceptition.PostNotExist;
import com.jsp.agro.exceptition.UserNotFound;
import com.jsp.agro.repo.PostRepo;
import com.jsp.agro.util.ResponseStructure;

@Service
public class CommentService {
	@Autowired
	private CommentDao dao;
	
	@Autowired
	private PostDao postdao;
	
	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private AgroDao  userdao;
	
	//saveComment
	public ResponseEntity<ResponseStructure<Comment>> saveComment(int pid, int uid, String msgcomment) {
		 Optional<Post> postdata = postrepo.findById(pid);
		if(postdata!=null) {
			User userdata = userdao.fetchById(uid);
			if(userdata!=null) {
				Comment cm=new Comment();
				cm.setComments(msgcomment);
				cm.setUser(userdata);
				Comment commentdata = dao.saveComment(cm);
				Post p = postdata.get();
				List<Comment> listcomment=new ArrayList<Comment>();
				listcomment.add(commentdata);
				listcomment.addAll(p.getComment());
				p.setComment(listcomment);
				postdao.updatePost(p);
				ResponseStructure<Comment> r=new ResponseStructure<Comment>();
				r.setData(commentdata);
				r.setMsg("comment posted successfully");
				r.setStatus(HttpStatus.CREATED.value());
				
				return new ResponseEntity<ResponseStructure<Comment>>(r,HttpStatus.CREATED);
			}
			else {
				throw new UserNotFound(uid+"is not present");
			}
		}
		else {
			throw new PostNotExist(pid+"is not present");
		}

	}

	public ResponseEntity<ResponseStructure<Comment>> deleteComment(int commentId) {
		Comment db =dao.deleteComment(commentId);
		if(db!=null) {
			ResponseStructure<Comment> r= new ResponseStructure<Comment>();
			r.setData(db);
			r.setMsg("deleted successfully");
			r.setStatus(HttpStatus.GONE.value());
			return new ResponseEntity<ResponseStructure<Comment>>(r,HttpStatus.GONE);
		}
		else {
			throw new UserNotFound(commentId+"is not present");
		}
	}
}
