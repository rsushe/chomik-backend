package com.fakecdek.delivery.service;


import com.fakecdek.deliverymockclient.dto.DeliveryAddressDto;
import com.fakecdek.deliverymockclient.dto.ApplyForDeliveryRequest;
import com.fakecdek.delivery.dto.Country;
import com.fakecdek.delivery.exception.InvalidCountryParameterException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AddressValidator {

    private final Set<Country> availableFromCountries = Set.of(
            Country.RUSSIA,
            Country.BELARUS,
            Country.CHINA,
            Country.SAUDI_ARABIA
    );

    private final Set<Country> availableToCountries = Set.of(
            Country.RUSSIA,
            Country.AUSTRALIA,
            Country.CHINA,
            Country.SAUDI_ARABIA,
            Country.BELARUS,
            Country.BELGIUM,
            Country.FINLAND
    );

    public void validateApplicationRequest(ApplyForDeliveryRequest request) throws InvalidCountryParameterException {
        validateAddress(request.userToAddress(), availableToCountries, "receiver address");
        validateAddress(request.userFromAddress(), availableFromCountries, "sender address");
    }

    private void validateAddress(DeliveryAddressDto address, Set<Country> availableCountries, String addressName) throws InvalidCountryParameterException {

        Country country = Country.fromCountryName(address.country());
        if (country == null) {
            throw new InvalidCountryParameterException("Couldn't find country of " + addressName + " with name " + address.country());
        }

        if (!availableCountries.contains(country)) {
            throw new InvalidCountryParameterException("Currently we aren't working with " + addressName + ": " + address.country());
        }


    }


}
