package com.project.demo.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
