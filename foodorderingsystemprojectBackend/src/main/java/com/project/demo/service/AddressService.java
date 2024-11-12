package com.project.demo.service;

import com.project.demo.models.Address;

public interface AddressService {
	
	Address saveAddress(Address address);
    Address findAddress(int id);

}
