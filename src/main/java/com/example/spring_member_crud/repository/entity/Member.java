package com.example.spring_member_crud.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Member {
    private Integer id;
    private String name;
    private Integer age;
    private String job;
    private String email;
}
