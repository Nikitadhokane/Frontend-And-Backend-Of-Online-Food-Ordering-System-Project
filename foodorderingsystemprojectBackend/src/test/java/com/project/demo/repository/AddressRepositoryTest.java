package com.project.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.demo.models.Address;
import com.project.demo.repos.AddressRepository;

@SpringBootTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void testCreateAddress() {
        Address address = new Address();
        address.setCity("City1");
        address.setState("State1");
        address.setZip("12345");
        address.setCountry("Country1");

        Address savedAddress = addressRepository.save(address);
        assertNotNull(savedAddress.getId());
        System.out.println("Test create address is running");
    }

    @Test
    public void testSaveAddress() {
        Address address = new Address();
        address.setCity("City1");
        address.setState("State1");
        address.setZip("12345");
        address.setCountry("Country1");

        Address savedAddress = addressRepository.save(address);
        assertNotNull(savedAddress.getId());
    }

    @Test
    public void testFindAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        assertNotNull(addresses);
    }

    @Test
    public void testGetAddressById() {
        Address address = new Address();
        address.setCity("City1");
        address.setState("State1");
        address.setZip("12345");
        address.setCountry("Country1");

        Address savedAddress = addressRepository.save(address);
        Integer addressId = savedAddress.getId();

        // Make sure to retrieve the address by its ID
        Address retrievedAddress = addressRepository.findById(addressId).orElse(null);

        assertNotNull(retrievedAddress);
        assertEquals(address.getCity(), retrievedAddress.getCity());
        assertEquals(address.getState(), retrievedAddress.getState());
        assertEquals(address.getZip(), retrievedAddress.getZip());
        assertEquals(address.getCountry(), retrievedAddress.getCountry());
    }

    @Test
    public void testUpdateAddress() {
        Address address = new Address();
        address.setCity("City1");
        address.setState("State1");
        address.setZip("12345");
        address.setCountry("Country1");

        Address savedAddress = addressRepository.save(address);
        Integer addressId = savedAddress.getId();

        Address retrievedAddress = addressRepository.findById(addressId).orElse(null);
        assertNotNull(retrievedAddress);

        retrievedAddress.setCity("City2");
        retrievedAddress.setState("State2");
        retrievedAddress.setZip("54321");
        retrievedAddress.setCountry("Country2");

        addressRepository.save(retrievedAddress);

        Address updatedAddress = addressRepository.findById(addressId).orElse(null);
        assertNotNull(updatedAddress);
        assertEquals("City2", updatedAddress.getCity());
        assertEquals("State2", updatedAddress.getState());
        assertEquals("54321", updatedAddress.getZip());
        assertEquals("Country2", updatedAddress.getCountry());
    }

    @Test
    public void testDeleteAddress() {
        Address address = new Address();
        address.setCity("City1");
        address.setState("State1");
        address.setZip("12345");
        address.setCountry("Country1");

        Address savedAddress = addressRepository.save(address);
        Integer addressId = savedAddress.getId();

        addressRepository.deleteById(addressId);

        Address deletedAddress = addressRepository.findById(addressId).orElse(null);
        assertNull(deletedAddress);
    }
}