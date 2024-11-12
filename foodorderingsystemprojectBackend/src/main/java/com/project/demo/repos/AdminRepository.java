package com.project.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

}
