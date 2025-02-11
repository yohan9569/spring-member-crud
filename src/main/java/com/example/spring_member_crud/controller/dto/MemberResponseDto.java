package com.example.spring_member_crud.controller.dto;

import com.example.spring_member_crud.repository.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(Include.NON_NULL) //JSON 노출 여부에 대한 조건
@JsonPropertyOrder({"userId", "username"}) //JSON 노출 프로퍼티(필드) 순서
public class MemberResponseDto {
    @JsonProperty("userId") //JSON 노출 프로퍼티(필드) 명칭
    private Integer id;
    @JsonProperty("username")
    private String name;
    @JsonIgnore //JSON 미노출 프로퍼티(필드)
    private Integer age;
    private String job;
    private String email;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateFormat
    private LocalDateTime createdAt;

    public static MemberResponseDto from(Member member) {
        return new MemberResponseDto(
            member.getId(),
            member.getName(),
            member.getAge(),
            member.getJob(),
            member.getEmail(),
            member.getCreateAt()
        );
    }
}
