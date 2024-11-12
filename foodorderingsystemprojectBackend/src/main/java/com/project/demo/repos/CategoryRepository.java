package com.project.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}