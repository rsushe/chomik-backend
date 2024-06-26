package com.chomik.chomikdelivery.service;


import com.chomik.chomikdelivery.domain.Address;
import com.chomik.chomikdelivery.repository.AddressRepository;
import com.chomik.chomikdelivery.service.mapper.AddressMapper;
import com.fakecdek.delivery.mock.model.dto.DeliveryAddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;


    @Transactional
    public Address saveAddress(DeliveryAddressDto addressDto) {
        return addressRepository.save(addressMapper.convertTo(addressDto));
    }
}
