package com.example.spring_member_crud.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private ExceptionType type;

    public CustomException(ExceptionType type) {
        super(type.getDesc());
        this.type = type;
    }

    public CustomException(ExceptionType type, Object message) {
        super(type.getDesc() + message.toString());
        this.type = type;
    }
}
