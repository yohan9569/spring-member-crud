package com.example.spring_member_crud.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

//@JsonFormat(shape = Shape.OBJECT)
@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum JobType {
    DEVELOPER("Developer", Arrays.asList("Frontend", "Backend")),
    ENGINEER("Engineer", Arrays.asList("DevOps", "SRE"));

    String name;
    List<String> titles;

    @JsonValue //특정 메서드 결과를 출력
    public String serialize() {
        return String.format("명칭 : %s | 종류 : %s", this.name, titles.toString());
    }
}
