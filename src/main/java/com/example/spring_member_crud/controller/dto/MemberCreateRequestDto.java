package com.example.spring_member_crud.controller.dto;

import com.example.spring_member_crud.repository.entity.Member;
import lombok.Setter;

@Setter
public class MemberCreateRequestDto {
    private String name;
    private Integer age;
    private JobType job;
    private String email;

    public Member toEntity(Integer id) {
        return Member.builder()
            .id(id)
            .name(this.name)
            .age(this.age)
            .job(this.job)
            .email(this.email)
            .build();
    }
}
