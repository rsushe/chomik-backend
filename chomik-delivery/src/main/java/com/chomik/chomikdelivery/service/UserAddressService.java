package com.chomik.chomikdelivery.service;

import com.chomik.chomikdelivery.domain.UserAddress;
import com.chomik.chomikdelivery.repository.UserAddressRepository;
import com.chomik.chomikdelivery.service.mapper.UserAddressMapper;
import com.chomik.delivery.client.dto.UserAddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private UserAddressMapper mapper;


    public List<UserAddressDto> getAllUsersAddresses(String userId) {
        List<UserAddress> userAddresses = userAddressRepository.findByUserId(userId);
        return userAddresses.stream()
                .map(mapper::convertTo)
                .collect(Collectors.toList());
    }

    public Optional<UserAddressDto> getUserAddressById(String id) {
        Optional<UserAddress> userAddressOptional = userAddressRepository.findById(id);
        return userAddressOptional.map(mapper::convertTo);
    }

    public UserAddressDto createUserAddress(UserAddressDto userAddressDto) {
        UserAddress userAddress = mapper.convertTo(userAddressDto);
        UserAddress savedUserAddress = userAddressRepository.save(userAddress);
        return mapper.convertTo(savedUserAddress);
    }

    @Transactional
    public UserAddressDto updateUserAddress(UserAddressDto userAddressDto) {
        Optional<UserAddress> optionalUserAddress = userAddressRepository.findById(userAddressDto.getId());
        if (optionalUserAddress.isPresent()) {
            UserAddress existingUserAddress = getUserAddress(userAddressDto, optionalUserAddress.get());

            UserAddress updatedUserAddress = userAddressRepository.save(existingUserAddress);
            return mapper.convertTo(updatedUserAddress);
        } else {
            throw new IllegalArgumentException("User Address with ID " + userAddressDto.getId() + " does not exist.");
        }
    }

    public void deleteUserAddress(String id) {
        userAddressRepository.deleteById(id);
    }

    private UserAddress getUserAddress(UserAddressDto dto, UserAddress entity) {
        entity.setUserId(dto.getUserId());
        entity.setCountry(dto.getCountry());
        entity.setCity(dto.getCity());
        entity.setStreet(dto.getStreet());
        entity.setHouse(dto.getHouse());
        entity.setFloor(dto.getFloor());
        entity.setFlat(dto.getFlat());
        entity.setExtraInfo(dto.getExtraInfo());
        return entity;
    }
}
