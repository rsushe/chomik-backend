package com.chomik.chomikdelivery.repository;


import com.chomik.chomikdelivery.domain.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, String> {
    List<UserAddress> findByUserId(String userId);

    Optional<UserAddress> findByIdAndUserId(String id, String userId);

    void deleteByIdAndUserId(String id, String userId);
}
