package com.project.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Admin {

	@Id
	private String userid;
	@JsonIgnore
	private String pwd;
	
	@Column(length = 30)
	private String uname;
	
	
	
	
	
}
