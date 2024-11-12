package com.project.demo.dtos;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.project.demo.models.Food;


public class FoodDTO extends Food {
	
	private MultipartFile pic;
	

	public MultipartFile getPic() {
		return pic;
	}


	public void setPic(MultipartFile pic) {
		this.pic = pic;
	}


	public static Food toEntity(FoodDTO dto) {
		Food entity=new Food();
		BeanUtils.copyProperties(dto, entity, "pic");		
		return entity;
	}
}
