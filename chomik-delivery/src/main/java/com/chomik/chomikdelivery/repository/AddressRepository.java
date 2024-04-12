package com.chomik.chomikdelivery.repository;

import com.chomik.chomikdelivery.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
