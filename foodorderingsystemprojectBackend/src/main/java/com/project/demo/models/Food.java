package com.project.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length=20)
	private String fname;
	@Column(length=20)
	private String descr;
	@ManyToOne
	@JoinColumn(name = "cat_id")
	private Category category;
	private int price;
	private String photo;
	
	public Food(int id) {
		this.id = id;
	}
	
}
