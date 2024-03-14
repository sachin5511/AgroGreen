package com.jsp.agro.dao;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.User;
import com.jsp.agro.repo.ImageRepo;

@Repository
public class ImageDao {
	@Autowired
	private ImageRepo repo;
	
	//savingImage
	public Image imageSave(Image image) {
		return repo.save(image);
	}
	
	//FetchingImage
	public Image fetchById(int id) {
		Optional<Image> imageData = repo.findById(id);
		if(imageData.isPresent()) {
			return imageData.get();
		}
		return null;
		
	}
	// update Image
	public Image updateById(int id, MultipartFile file ) throws IOException {
		 Image image = new Image();
		 image.setData(file.getBytes());
		 image.setName(file.getOriginalFilename());
		 Optional<Image> db = repo.findById(id);
		 Image data = db.get();
		 if(db.isPresent()) {
			 if(image.getId()==0) {
				 image.setId(data.getId());
			 }
			 if(image.getData()==null) {
				 image.setData(data.getData());
			 }
			 if(image.getName()==null) {
				 image.setName(data.getName());
			 }
			 return repo.save(image);
		 }else {
			 return null;
		 }
	}
	
	//deleteImage
	public String deleteImageById(int id) {
		Optional<Image> db = repo.findById(id);
		if(db.isEmpty()) {
		return null;
		}else {
			repo.deleteById(id);
			return "Data delete Successfully";
		}
	}
}
