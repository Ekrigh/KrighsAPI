package com.erikkrigh.krighsapi.DAO;

import com.erikkrigh.krighsapi.DTO.MemberDTO;
import com.erikkrigh.krighsapi.models.Member;

import java.util.List;

public interface MemberDAO {
    List<Member> findAll();

    List<MemberDTO> findAllForMembers();

    Member findById(int id);

    Member save(Member member);

    void deleteById(int id);
}