package com.example.spring_member_crud.controller.dto;

import com.example.spring_member_crud.exception.CustomException;
import com.example.spring_member_crud.exception.ExceptionType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum JobType {
    DEVELOPER("Developer", Arrays.asList("Frontend", "Backend")),
    ENGINEER("Engineer", Arrays.asList("DevOps", "SRE"));

    String name;
    List<String> titles;

    @JsonCreator(mode = Mode.DELEGATING) //단일 속성, 단일 단순 값 json을 받음.
    public static JobType deserialize(String name) { //json 속성명이 name 아녀도 된다.
        for (JobType each : JobType.values()) {
            if (each.getName().equals(name)) {
                return each;
            }
        }
        throw new CustomException(ExceptionType.INVALID_INPUT, "JobType 내 해당하는 Enum 이 존재하지 않습니다. name : " + name);
    }
}
