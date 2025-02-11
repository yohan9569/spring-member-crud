package com.example.spring_member_crud.exception;

public class MemberNotFoundException extends RuntimeException {
    private static final String message = "유저를 찾을 수 없습니다. ID: ";

    public MemberNotFoundException(Integer id) {
        super(message + id);
    }
}
