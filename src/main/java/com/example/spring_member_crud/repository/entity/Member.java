package com.example.spring_member_crud.repository.entity;

import com.example.spring_member_crud.controller.dto.JobType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Member {
    private Integer id;
    private String name;
    private Integer age;
    private JobType job;
    private String email;
    private LocalDateTime createAt;
}
