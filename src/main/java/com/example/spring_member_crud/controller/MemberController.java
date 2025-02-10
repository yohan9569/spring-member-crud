package com.example.spring_member_crud.controller;

import com.example.spring_member_crud.controller.dto.MemberCreateRequestDto;
import com.example.spring_member_crud.controller.dto.MemberPatchRequestDto;
import com.example.spring_member_crud.controller.dto.MemberResponseDto;
import com.example.spring_member_crud.service.MemberService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value = "")
    public MemberResponseDto create(@RequestBody MemberCreateRequestDto dto) {
        return memberService.create(dto);
    }

    @GetMapping(value = "/{id}")
    public MemberResponseDto getMemberById(@PathVariable Integer id) {
        return memberService.getMemberById(id);
    }

    @GetMapping(value="")
    public List<MemberResponseDto> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PatchMapping(value="/{id}")
    public MemberResponseDto updateMember(@PathVariable Integer id, @ModelAttribute MemberPatchRequestDto dto) {
        return memberService.updateMember(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteMember(@PathVariable Integer id) {
        boolean result = memberService.deleteMember(id);
        return result ? "delete success" : "delete fail";
    }
}
