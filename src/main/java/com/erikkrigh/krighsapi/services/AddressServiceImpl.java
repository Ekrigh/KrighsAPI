package com.erikkrigh.krighsapi.services;

import com.erikkrigh.krighsapi.DAO.AddressDAO;
import com.erikkrigh.krighsapi.models.Address;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressDAO addressDAO;

    @Autowired
    public AddressServiceImpl(AddressDAO addrDAO) {
        addressDAO = addrDAO;
    }

    @Transactional
    @Override
    public void save(Address address) {
        addressDAO.save(address);
    }

    @Transactional
    @Override
    public void delete(Address address) {
        addressDAO.delete(address);
    }

    @Override
    public Address findByDetails(String street, String postalcode, String city) {
        return addressDAO.findByDetails(street, postalcode, city);
    }
}
