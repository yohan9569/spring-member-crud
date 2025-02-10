package com.example.spring_member_crud.controller.dto;

import com.example.spring_member_crud.repository.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponseDto {
    private Integer id;
    private String name;
    private Integer age;
    private String job;
    private String email;

    public static MemberResponseDto from(Member member) {
        return new MemberResponseDto(
            member.getId(),
            member.getName(),
            member.getAge(),
            member.getJob(),
            member.getEmail()
        );
    }
}
