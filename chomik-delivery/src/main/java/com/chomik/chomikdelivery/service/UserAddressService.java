package com.chomik.chomikdelivery.service;

import com.chomik.chomikdelivery.domain.Address;
import com.chomik.chomikdelivery.domain.UserAddress;
import com.chomik.chomikdelivery.repository.UserAddressRepository;
import com.chomik.chomikdelivery.service.mapper.UserAddressMapper;
import com.chomik.delivery.client.dto.CreateUserAddressRequest;
import com.chomik.delivery.client.dto.UserAddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private AddressService addressService;

    public List<UserAddressDto> getAllUsersAddresses(String userId) {
        List<UserAddress> userAddresses = userAddressRepository.findByUserId(userId);
        return userAddresses.stream()
                .map(userAddressMapper::convertTo)
                .collect(Collectors.toList());
    }

    public Optional<UserAddressDto> getUserAddressById(String id) {
        Optional<UserAddress> userAddressOptional = userAddressRepository.findById(id);
        return userAddressOptional.map(userAddressMapper::convertTo);
    }

    public Optional<UserAddressDto> getUserAddressByIdAndUserId(String id, String userId) {
        Optional<UserAddress> userAddressOptional = userAddressRepository.findByIdAndUserId(id, userId);
        return userAddressOptional.map(userAddressMapper::convertTo);
    }

    public UserAddressDto createUserAddress(CreateUserAddressRequest createUserAddressRequest) {
        Address addressEntity = addressService.saveAddress(createUserAddressRequest.getAddress());

        UserAddress userAddress = new UserAddress(createUserAddressRequest.getUserId(), addressEntity);
        UserAddress savedUserAddress = userAddressRepository.save(userAddress);
        return userAddressMapper.convertTo(savedUserAddress);
    }

    public void deleteUserAddress(String id, String userId) {
        userAddressRepository.deleteByIdAndUserId(id, userId);
    }
}
