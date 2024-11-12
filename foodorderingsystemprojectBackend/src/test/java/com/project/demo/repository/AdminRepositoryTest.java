package com.project.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.demo.models.Admin;
import com.project.demo.repos.AdminRepository;

@SpringBootTest
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void testCreateAdmin() {
        Admin admin = new Admin();
        admin.setUserid("admin1");
        admin.setPwd("pass123");
        admin.setUname("Admin One");

        Admin savedAdmin = adminRepository.save(admin);
        assertNotNull(savedAdmin.getUserid());
        System.out.println("Test create admin is running");
    }

    @Test
    public void testSaveAdmin() {
        Admin admin = new Admin();
        admin.setUserid("admin1");
        admin.setPwd("pass123");
        admin.setUname("Admin One");

        Admin savedAdmin = adminRepository.save(admin);
        assertNotNull(savedAdmin.getUserid());
    }

    @Test
    public void testFindAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        assertNotNull(admins);
    }

    @Test
    public void testGetAdminById() {
        Admin admin = new Admin();
        admin.setUserid("admin1");
        admin.setPwd("pass123");
        admin.setUname("Admin One");

        Admin savedAdmin = adminRepository.save(admin);
        String adminId = savedAdmin.getUserid();

        // retrieve the admin by its ID
        Admin retrievedAdmin = adminRepository.findById(adminId).orElse(null);

        assertNotNull(retrievedAdmin);
        assertEquals(admin.getUserid(), retrievedAdmin.getUserid());
        assertEquals(admin.getPwd(), retrievedAdmin.getPwd());
        assertEquals(admin.getUname(), retrievedAdmin.getUname());
    }

    @Test
    public void testUpdateAdmin() {
        Admin admin = new Admin();
        admin.setUname("admin1");
        admin.setPwd("pass12");
        admin.setUserid("admin@gmail.com");
        

        Admin savedAdmin = adminRepository.save(admin);
        String adminId = savedAdmin.getUserid();

        Admin retrievedAdmin = adminRepository.findById(adminId).orElse(null);
        assertNotNull(retrievedAdmin);

        retrievedAdmin.setUname("newAdmin1");
        retrievedAdmin.setPwd("npass1");
        retrievedAdmin.setUserid("newadmin@gmail.com");
       

        adminRepository.save(retrievedAdmin);

        Admin updatedAdmin = adminRepository.findById(adminId).orElse(null);
        assertNotNull(updatedAdmin);
        assertEquals("newAdmin1", updatedAdmin.getUname());
        assertEquals("npass1", updatedAdmin.getPwd());
        assertEquals("newadmin@gmail.com", updatedAdmin.getUserid());
        
    }

    @Test
    public void testDeleteAdmin() {
        Admin admin = new Admin();
        admin.setUserid("admin1");
        admin.setPwd("pass123");
        admin.setUname("Admin One");

        Admin savedAdmin = adminRepository.save(admin);
        String adminId = savedAdmin.getUserid();

        adminRepository.deleteById(adminId);

        Admin deletedAdmin = adminRepository.findById(adminId).orElse(null);
        assertNull(deletedAdmin);
    }
}