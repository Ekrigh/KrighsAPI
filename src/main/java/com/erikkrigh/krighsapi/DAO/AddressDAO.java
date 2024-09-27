package com.erikkrigh.krighsapi.DAO;

import com.erikkrigh.krighsapi.DTO.MemberDTO;
import com.erikkrigh.krighsapi.models.Address;
import com.erikkrigh.krighsapi.models.Member;

import java.util.List;

public interface AddressDAO {

    void save(Address address);

    void delete(Address address);

    Address findByDetails(String street, String postalcode, String city);
}
