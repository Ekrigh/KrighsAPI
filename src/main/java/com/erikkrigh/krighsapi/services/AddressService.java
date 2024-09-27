package com.erikkrigh.krighsapi.services;

import com.erikkrigh.krighsapi.models.Address;

public interface AddressService {
    void save(Address address);

    void delete(Address address);

    Address findByDetails(String street, String postalcode, String city);
}
