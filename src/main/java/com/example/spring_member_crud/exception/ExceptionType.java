package com.example.spring_member_crud.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum ExceptionType {
    USER_NOT_FOUND("A04", "유저가 데이터베이스 내 존재하지 않습니다. 유저 id : ", HttpStatus.NOT_FOUND, Level.WARN),
    PAYMENT_NOT_FOUND("A05", "결제 데이터가 데이터베이스 내 존재하지 않습니다. 결제 id : ", HttpStatus.NOT_FOUND, Level.WARN),
    STATUS_NOT_FOUND("A06", "상태가 데이터베이스 내 존재하지 않습니다. 상태 id : ", HttpStatus.NOT_FOUND, Level.WARN),
    INVALID_INPUT("B01", "값을 제대로 좀 주세요 : ", HttpStatus.BAD_REQUEST, Level.WARN),
    UNCLASSIFIED_ERROR("Z10", "내부에서 에러가 발생했습니다. 추가 메세지 : ", HttpStatus.INTERNAL_SERVER_ERROR, Level.ERROR);

    String type;
    String desc;
    HttpStatus status;
    Level level;
}
