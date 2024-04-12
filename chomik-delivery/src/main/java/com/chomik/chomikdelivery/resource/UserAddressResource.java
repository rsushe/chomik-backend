package com.chomik.chomikdelivery.resource;


import com.chomik.chomikdelivery.service.UserAddressService;
import com.chomik.chomikdelivery.service.dto.CreateUserAddressRequest;
import com.chomik.delivery.client.dto.UserAddressDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/delivery/address")
public class UserAddressResource {

    @Autowired
    private UserAddressService userAddressService;


    @GetMapping
    public ResponseEntity<?> getUsersAddresses(@RequestParam String userId) {
        try {
            List<UserAddressDto> usersAddresses = userAddressService.getAllUsersAddresses(userId);
            return ResponseEntity.ok(usersAddresses);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Couldt't get user's addresses. Cause: " + e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserAddressById(@PathVariable String id) {
        try {
            Optional<UserAddressDto> userAddress = userAddressService.getUserAddressById(id);
            return userAddress.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Couldt't get address with id " + id + ". Cause: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createUserAddress(@RequestBody @Valid CreateUserAddressRequest createUserAddressRequest) {
        try {
            UserAddressDto createdUserAddress = userAddressService.createUserAddress(createUserAddressRequest);
            return new ResponseEntity<>(createdUserAddress, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Couldt't create UserAddress. Cause: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserAddress(@PathVariable String id) {
        try {
            userAddressService.deleteUserAddress(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Couldt't delete address with id " + id + ". Cause: " + e.getMessage());
        }
    }
}
