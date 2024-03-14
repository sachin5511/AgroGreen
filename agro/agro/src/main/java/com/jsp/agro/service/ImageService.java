package com.jsp.agro.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.dao.AgroDao;
import com.jsp.agro.dao.ImageDao;
import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.User;
import com.jsp.agro.exceptition.DataNotFound;
import com.jsp.agro.exceptition.DataNotUpdate;
import com.jsp.agro.exceptition.ImageNotDelete;
import com.jsp.agro.util.ResponseStructure;
@Service
public class ImageService {
	@Autowired
	private ImageDao dao;
	
	@Autowired
	private AgroDao userdao;
	
	//SaveImage
	public Image imageSave(int id,MultipartFile file) throws IOException {
		User userdb = userdao.fetchById(id);
		if(userdb!=null) {
			Image image = new Image();
			image.setData(file.getBytes());
			image.setName(file.getOriginalFilename());
			Image data = dao.imageSave(image);
			if(data!=null) {
				userdb.setImage(data);
				userdao.updateById(userdb);
				return dao.imageSave(image);
        	 }
         }
		throw new DataNotFound(id+" : is not present");
	}
	//Fetching image
	public ResponseEntity<byte[]> fetchById(int id) {
		Image db = dao.fetchById(id);
		if(db!=null) {
			byte[] imagedata = db.getData();
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagedata);
		}
		else {
			throw new DataNotFound(id+":is not Present");
		}
	}
	
	//update image
	public ResponseEntity<ResponseStructure<Image>> updateById( int id,MultipartFile file) throws IOException {
		 Image update = dao.fetchById(id);
		 System.out.println(update);
		 if(update !=null) {
			Image db = dao.updateById(id,file);
			ResponseStructure<Image> m = new ResponseStructure<Image>();
			m.setData(db);
			m.setMsg(" Image Updated successfully.....");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Image>>(m,HttpStatus.OK) ;
		 }
		 throw new DataNotUpdate(id+" : is not present");
	}
	//deleteImageById
	public String deleteImageById(int id) {
		Image imageDelete = dao.fetchById(id);
		List<User> userAll = userdao.fetchByAll();
		for (Iterator iterator = userAll.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			Image ud = user.getImage();
			if(ud!=null) {
				if(ud.getId() == id) {
					user.setImage(null);
					userdao.updateById(user);
				}
			}
		}
		if(imageDelete==null) {
			 throw new ImageNotDelete(id+" : is not present");
		 }
		return dao.deleteImageById(id);
	}
}
