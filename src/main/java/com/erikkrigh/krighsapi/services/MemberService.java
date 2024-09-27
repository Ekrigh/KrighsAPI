package com.erikkrigh.krighsapi.services;

import com.erikkrigh.krighsapi.DTO.MemberDTO;
import com.erikkrigh.krighsapi.models.Member;

import java.util.List;

public interface MemberService {
    List<Member> findAll();

    List<MemberDTO> findAllForMembers();

    Member findById(int id);

    Member save(Member member);

    void deleteById(int id);
}
