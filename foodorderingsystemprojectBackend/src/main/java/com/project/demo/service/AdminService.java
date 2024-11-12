package com.project.demo.service;

import com.project.demo.models.Admin;

public interface AdminService {
	
	Admin validate(String userid, String pwd);
    void saveAdmin(Admin admin);
    void updateAdmin(Admin admin);
    long count();
}


