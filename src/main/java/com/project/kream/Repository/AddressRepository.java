package com.project.kream.Repository;

import com.project.kream.Model.Entity.Address;
import com.project.kream.Model.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByCustomerId(Long id);

}
