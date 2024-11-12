package com.project.demo.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.models.Admin;
import com.project.demo.repos.AdminRepository;
import com.project.demo.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository dao;

    @Override
    public Admin validate(String userid, String pwd) {
        Optional<Admin> admin = dao.findById(userid);
        if (admin.isPresent() && admin.get().getPwd().equals(pwd)) {
            return admin.get();
        }
        return null;
    }

    @Override
    public void saveAdmin(Admin admin) {
        dao.save(admin);
    }

    @Override
    public void updateAdmin(Admin admin) {
        if (admin.getPwd().equals("someString") || admin.getPwd() == null) {
            admin.setPwd(dao.findById(admin.getUserid()).get().getPwd());
        }
        dao.save(admin);
    }

    @Override
    public long count() {
        return dao.count();
    }
}