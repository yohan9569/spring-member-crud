package com.example.spring_member_crud.controller;

import com.example.spring_member_crud.controller.dto.MemberCreateRequestDto;
import com.example.spring_member_crud.controller.dto.MemberPatchRequestDto;
import com.example.spring_member_crud.controller.dto.MemberResponseDto;
import com.example.spring_member_crud.controller.dto.common.BaseResponse;
import com.example.spring_member_crud.exception.CustomException;
import com.example.spring_member_crud.exception.ExceptionType;
import com.example.spring_member_crud.service.MemberService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value = "")
    public BaseResponse<MemberResponseDto> create(@RequestBody @Validated MemberCreateRequestDto dto) {
        // 중앙 예외 처리로 인해, try-catch 삭제. success 로직만 구현.
        MemberResponseDto member = memberService.create(dto);
        return BaseResponse.success(member);
    }

    @GetMapping(value = "/{id}")
    public BaseResponse<MemberResponseDto> getMemberById(@PathVariable Integer id) {
        MemberResponseDto member = memberService.getMemberById(id);
        return BaseResponse.success(member);
    }

    @GetMapping(value = "")
    public List<MemberResponseDto> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PatchMapping(value = "/{id}")
    public MemberResponseDto updateMember(@PathVariable Integer id, @ModelAttribute MemberPatchRequestDto dto) {
        return memberService.updateMember(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteMember(@PathVariable Integer id) {
        boolean result = memberService.deleteMember(id);
        return result ? "delete success" : "delete fail";
    }
}
