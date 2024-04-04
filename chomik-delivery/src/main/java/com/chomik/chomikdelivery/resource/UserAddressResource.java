package com.chomik.chomikdelivery.resource;


import com.chomik.chomikdelivery.service.UserAddressService;
import com.chomik.chomikdelivery.service.dto.UserAddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserAddressResource {

    @Autowired
    private UserAddressService userAddressService;


    @GetMapping("/{id}")
    public ResponseEntity<UserAddressDto> getUserAddressById(@PathVariable String id) {
        Optional<UserAddressDto> userAddress = userAddressService.getUserAddressById(id);
        return userAddress.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserAddressDto> createUserAddress(@RequestBody UserAddressDto userAddressDto) {
        UserAddressDto createdUserAddress = userAddressService.createUserAddress(userAddressDto);
        return new ResponseEntity<>(createdUserAddress, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserAddressDto> updateUserAddress(@RequestBody UserAddressDto userAddressDto) {
        UserAddressDto updatedUserAddress = userAddressService.updateUserAddress(userAddressDto);
        return new ResponseEntity<>(updatedUserAddress, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAddress(@PathVariable String id) {
        userAddressService.deleteUserAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
