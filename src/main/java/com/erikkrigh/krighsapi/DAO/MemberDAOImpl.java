package com.erikkrigh.krighsapi.DAO;

import com.erikkrigh.krighsapi.DTO.MemberDTO;
import com.erikkrigh.krighsapi.models.Address;
import com.erikkrigh.krighsapi.models.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO {
    private EntityManager entityManager;

    @Autowired
    public void MemberDAOImpl(EntityManager entityMan) {
        entityManager = entityMan;
    }

    @Override
    public List<Member> findAll() {
        TypedQuery<Member> query = entityManager.createQuery("FROM Member", Member.class);
        List<Member> members = query.getResultList();
        return members;
    }

    @Override
    public List<MemberDTO> findAllForMembers() {
        TypedQuery<MemberDTO> query = entityManager.createQuery(
                "SELECT new com.erikkrigh.krighsapi.DTO.MemberDTO(m.firstName, m.lastName, m.address, m.email, m.phone) FROM Member m",
                MemberDTO.class
        );
        return query.getResultList();
    }

    @Override
    public Member findById(int id) {
        Member member = entityManager.find(Member.class, id);
        return member;
    }

    @Override
    public List<Member> findAllByAddress(Address address) {
        TypedQuery<Member> query = entityManager.createQuery(
                "FROM Member WHERE address = :address", Member.class);
        query.setParameter("address", address);
        return query.getResultList();
    }

    @Override
    public Member save(Member memb) {
        Member member = entityManager.merge(memb); //Om id==0 sparas objektet. Annars uppdateras det
        return member;
    }

    @Override
    public void deleteById(int id) {
        Member member = entityManager.find(Member.class, id);
        entityManager.remove(member);
    }


}