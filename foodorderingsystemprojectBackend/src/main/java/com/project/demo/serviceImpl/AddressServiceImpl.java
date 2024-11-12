package com.project.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.models.Address;
import com.project.demo.repos.AddressRepository;
import com.project.demo.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository dao;

    @Override
    public Address saveAddress(Address address) {
        return dao.save(address);
    }

    @Override
    public Address findAddress(int id) {
        return dao.findById(id).orElse(null);
    }
}