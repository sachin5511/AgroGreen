package com.jsp.agro.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jsp.agro.entity.Post;
import com.jsp.agro.entity.User;
import com.jsp.agro.repo.PostRepo;

@Repository
public class PostDao {
	@Autowired
	private PostRepo repo;
	
	//User Post 
	public Post saveUserPost(Post post) {
		return repo.save(post);
	}

	public Post fetchById(int id) {
		Optional<Post> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		else {
			return null;
		}
	}
	//deleteImage
		public Post deletepostById(int id) {
			Optional<Post> db = repo.findById(id);
			if(db.isEmpty()) {
			return null;
			}else {
				repo.deleteById(id);
				return db.get();
			}
		}

		public Post updatePost(Post post) {
			 Optional<Post> db = repo.findById(post.getId());
			 Post data = db.get();
			 if(db.isPresent()) {
				 if(post.getLikes()==0) {
					 post.setLikes(data.getLikes());
				 }
				 if(post.getComment()==null) {
					 post.setComment(data.getComment());
				 }
				 if(post.getImage()==null) {
					 post.setImage(data.getImage());
				 }
				 if(post.getDate()==null) {
					 post.setDate(data.getDate());
				 }
				 if(post.getCaption()==null) {
					 post.setCaption(data.getCaption());
				 }
				 if(post.getLocation()==null) {
					 post.setLocation(data.getLocation());
				 }
				 return repo.save(post);
			 }
			 else {
				 return null;
			 }
		}


}
