package com.example.spring_member_crud.controller.dto;

import com.example.spring_member_crud.repository.entity.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Setter;


@JsonInclude(JsonInclude.Include.NON_NULL) // null 필드는 JSON 요청에서 제외
@Setter
public class MemberPatchRequestDto {
    private String name;
    private Integer age;
    private String job;
    private String email;

    public Member updateEntity(Member member) {
        if (this.name != null) member.setName(this.name);
        if (this.age != null) member.setAge(this.age);
        if (this.job != null) member.setJob(this.job);
        if (this.email != null) member.setEmail(this.email);

        return member;
    }
}