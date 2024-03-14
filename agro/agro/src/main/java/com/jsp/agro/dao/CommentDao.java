package com.jsp.agro.dao;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Comment;
import com.jsp.agro.entity.Post;
import com.jsp.agro.repo.CommentRepo;
import com.jsp.agro.repo.PostRepo;

@Repository
public class CommentDao {
	
	@Autowired
	private CommentRepo repo;
	
	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private PostDao postDao;
	
	//save
	public Comment saveComment(Comment comment) {
		return repo.save(comment);	
	}
//	delete
	public Comment deleteComment(int commentId) {
	     List<Post> posts = postrepo.findAll();
	    for (Post post : posts) {
	    	List<Comment> comments = post.getComment();
	    	Iterator<Comment> iterator = comments.iterator();
	        while (iterator.hasNext()) {
	        	Comment comment = iterator.next();
	        	if (comment.getId() == commentId) {
	               iterator.remove();
	                postDao.updatePost(post);
	                comment.setUser(null);
	                repo.deleteById(commentId);
	                return comment;
	            }
	        }
	    }
	    return null;
	}


}
