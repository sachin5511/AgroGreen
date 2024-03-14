package com.jsp.agro.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.dao.AgroDao;
import com.jsp.agro.dao.PostDao;
import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.Post;
import com.jsp.agro.entity.User;
import com.jsp.agro.exceptition.DataNotFound;
import com.jsp.agro.exceptition.ImageNotDelete;
import com.jsp.agro.exceptition.PostNotExist;
import com.jsp.agro.exceptition.UserNotFound;
import com.jsp.agro.util.ResponseStructure;

@Service
public class PostService {
	
	@Autowired
	private PostDao dao;
	
	@Autowired
	private AgroDao userdao;
	
	@Autowired
	private ImageService imageservice;
	
	//saving userPost
	public ResponseEntity<ResponseStructure<Post>> saveUserPost(int id, MultipartFile file,String date,
			String caption, String location) throws IOException {
		User userdata = userdao.fetchById(id);
		if(userdata!=null) {
			Image data = imageservice.imageSave(id, file);
			Post userpost = new Post();
			userpost.setImage(data);
			userpost.setDate(date);
			userpost.setCaption(caption);
			userpost.setLocation(location);
			Post postdata = dao.saveUserPost(userpost);
			ResponseStructure<Post> m = new ResponseStructure<Post>();
			if(postdata!=null) {
				 ArrayList<Post> postlist = new ArrayList<Post>();
				 postlist.add(userpost);
				 postlist.addAll(userdata.getUserpost());
				userdata.setUserpost( postlist);
				userdao.updateById(userdata);
				m.setData(postdata);
				m.setMsg("Post submitted successfully");
				m.setStatus(HttpStatus.OK.value());
			}
			return new ResponseEntity<ResponseStructure<Post>>(m,HttpStatus.OK);
		}
		throw new UserNotFound(id+" : is not present");
	}

	//fetching post
	public ResponseEntity<ResponseStructure<Post>> fetchById(int id) {
		 Post db = dao.fetchById(id);
		ResponseStructure<Post> m = new ResponseStructure<Post>();
		if(db!=null) {
			m.setData(db);
			m.setMsg(" Fetching successfully.....");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Post>>(m,HttpStatus.OK) ;
		}
		else {
			throw new DataNotFound(id+":is not Present");
		}
	}

	//delete image
	public ResponseEntity<ResponseStructure<Post>> deleteById(int id) {
		Post userpostid = dao.fetchById(id);
		List<User> userall = userdao.fetchByAll();
		for (Iterator iterator = userall.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			List<Post> userpostdata = user.getUserpost();
			for (Iterator iterator2 = userpostdata.iterator(); iterator2.hasNext();) {
				Post userpost = (Post) iterator2.next();
				if(userpost.getId()==id) {
					iterator2.remove();
					imageservice.deleteImageById(id);
					userdao.updateById(user);
				}
			}
		}
		Post deletedata = dao.deletepostById(id);
		ResponseStructure<Post> m = new ResponseStructure<Post>();
		 if(deletedata!=null) {
			 m.setData(userpostid);
			 m.setMsg(" Post deleted........");
			 m.setStatus(HttpStatus.OK.value());
			 return new ResponseEntity<ResponseStructure<Post>>(m,HttpStatus.OK);
		 }
		 throw new PostNotExist(id+":is not Present");
	}
}
