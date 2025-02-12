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
        try {
            MemberResponseDto member = memberService.create(dto);
            return BaseResponse.of(true, null, null, member);
        } catch (CustomException e) {
            log.warn(e.getMessage(), e); //Exception 예외 처리에서 가장 중요한것은 Logging 로깅
            return BaseResponse.of(false, e.getType().getType(), e.getType().getDesc(), null);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return BaseResponse.of(false,
                ExceptionType.UNCLASSIFIED_ERROR.getType(),
                ExceptionType.UNCLASSIFIED_ERROR.getDesc(), null);
        }
    }

    @GetMapping(value = "/{id}")
    public BaseResponse<MemberResponseDto> getMemberById(@PathVariable Integer id) {
        try {
            MemberResponseDto member = memberService.getMemberById(id);
            return BaseResponse.of(true, null, null, member);
        } catch (CustomException e) {
            log.warn(e.getMessage(), e);
            return BaseResponse.of(false, e.getType().getType(), e.getType().getDesc(), null);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return BaseResponse.of(false,
                ExceptionType.UNCLASSIFIED_ERROR.getType(),
                ExceptionType.UNCLASSIFIED_ERROR.getDesc(), null);
        }
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
