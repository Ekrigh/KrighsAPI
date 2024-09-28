package com.erikkrigh.krighsapi.controllers;

import com.erikkrigh.krighsapi.DTO.MemberDTO;
import com.erikkrigh.krighsapi.exceptions.MemberNotFoundException;
import com.erikkrigh.krighsapi.models.Member;
import com.erikkrigh.krighsapi.services.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memService) {
        memberService = memService;
    }

    @GetMapping("/admin/members")
    @PreAuthorize("hasRole('client_ADMIN')")
    public List<Member> findAll() {
        return memberService.findAll();
    }

    @GetMapping("/admin/members/{id}")
    @PreAuthorize("hasRole('client_ADMIN')")
    public Member getMember(@PathVariable int id) {
        Member member = memberService.findById(id);
        if (member == null) {
            throw new MemberNotFoundException("member with id: " + id + " doesn't exist");
        }
        return member;
    }

    @PutMapping("/admin/members/{id}")
    @PreAuthorize("hasRole('client_ADMIN')")
    public ResponseEntity<Member> updateMember(@PathVariable("id") int id, @Valid @RequestBody Member member) {
        member.setId(id);
        return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
    }

    @PostMapping("/admin/members")
    @PreAuthorize("hasRole('client_ADMIN')")
    public ResponseEntity<Member> addMember(@Valid @RequestBody Member member) {
        member.setId(0);
        return new ResponseEntity<Member>(memberService.save(member), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/members/{id}")
    @PreAuthorize("hasRole('client_ADMIN')")
    public String deleteMember(@PathVariable int id) {
        Member member = memberService.findById(id);
        if (member == null) {

            throw new MemberNotFoundException("member with id: " + id + " doesn't exist");
        }
        memberService.deleteById(id);
        return "member with id: " + id + " was deleted";
    }

    @GetMapping("/mypages/members")
    @PreAuthorize("hasRole('client_MEMBER')")
    public List<MemberDTO> findAllForMembers() {
        return memberService.findAllForMembers();
    }

    @PutMapping("/mypages/members/{id}")
    @PreAuthorize("hasRole('client_MEMBER')")
    public ResponseEntity<Member> updateCurrentMember(@PathVariable("id") int id, @Valid @RequestBody Member member) {
        member.setId(id);
        return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
    }

}