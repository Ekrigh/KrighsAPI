package com.erikkrigh.krighsapi.DAO;

import com.erikkrigh.krighsapi.models.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDAOImpl implements AddressDAO {
    private EntityManager entityManager;

    @Autowired
    public AddressDAOImpl(EntityManager entityMan) {
        entityManager = entityMan;
    }

    @Override
    public void save(Address address) {
        entityManager.persist(address);
    }

    @Override
    public void delete(Address address) {
        entityManager.remove(address);
    }

    @Override
    public Address findByDetails(String street, String postalcode, String city) {
        TypedQuery<Address> query = entityManager.createQuery(
                "FROM Address WHERE street = :street AND postalcode = :postalcode AND city = :city", Address.class);
        query.setParameter("street", street);
        query.setParameter("postalcode", postalcode);
        query.setParameter("city", city);

        List<Address> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
