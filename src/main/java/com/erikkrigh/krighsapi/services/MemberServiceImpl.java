package com.erikkrigh.krighsapi.services;

import com.erikkrigh.krighsapi.DAO.MemberDAO;
import com.erikkrigh.krighsapi.DTO.MemberDTO;
import com.erikkrigh.krighsapi.models.Address;
import com.erikkrigh.krighsapi.models.Member;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberDAO memberDAO;
    private AddressService addressService;

    @Autowired
    public MemberServiceImpl(MemberDAO memDAO, AddressService addrService) {
        memberDAO = memDAO;
        addressService = addrService;
    }

    @Override
    public List<Member> findAll() {
        return memberDAO.findAll();
    }

    @Override
    public List<MemberDTO> findAllForMembers() {
        return memberDAO.findAllForMembers();
    }

    @Override
    public Member findById(int id) {
        return memberDAO.findById(id);
    }

    @Transactional
    @Override
    public Member save(Member member) {
        Address address = member.getAddress();
        Member existingMember = memberDAO.findById(member.getId());
        Address oldAddress = existingMember != null ? existingMember.getAddress() : null;

        if (address != null) {
            Address existingAddress = addressService.findByDetails(address.getStreet(), address.getPostalcode(), address.getCity());

            if (existingAddress != null) {
                member.setAddress(existingAddress);
            } else {
                addressService.save(address);
            }
        }

        Member savedMember = memberDAO.save(member);

        //check if address was changed and if old address is orphaned
        if (oldAddress != null && !oldAddress.equals(address)) {
            List<Member> membersWithSameAddress = memberDAO.findAllByAddress(oldAddress);
            if (membersWithSameAddress.isEmpty()) {
                addressService.delete(oldAddress);
            }
        }
        return savedMember;
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        Member member = memberDAO.findById(id);
        if (member != null) {
            Address address = member.getAddress();
            memberDAO.deleteById(id);

            List<Member> membersWithSameAddress = memberDAO.findAllByAddress(address);
            if (membersWithSameAddress.isEmpty()) {
                addressService.delete(address);
            }
        }
    }
}