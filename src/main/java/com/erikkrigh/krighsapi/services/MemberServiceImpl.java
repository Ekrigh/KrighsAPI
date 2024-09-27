package com.erikkrigh.krighsapi.services;

import com.erikkrigh.krighsapi.DAO.MemberDAO;
import com.erikkrigh.krighsapi.DTO.MemberDTO;
import com.erikkrigh.krighsapi.models.Member;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberDAO memberDAO;

    @Autowired
    public MemberServiceImpl(MemberDAO memDAO) {
        memberDAO = memDAO;
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
        return memberDAO.save(member);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        memberDAO.deleteById(id);
    }
}