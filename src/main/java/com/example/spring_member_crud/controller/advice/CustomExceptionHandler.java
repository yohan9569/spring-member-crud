/*
 * 사용 이유
 * 1. 중복 로직
 * 2. MessageConverter 직렬/역직렬화시 발생하는 Exception 처리 불가
 *
 * 사용 방법
 * (1) @ControllerAdvice 통해 "타겟 Controller" 설정
 * (2) @ExceptionHandler 통해 "특정 Exception" 에 대한 처리 로직
 */
package com.example.spring_member_crud.controller.advice;

import com.example.spring_member_crud.controller.dto.common.BaseResponse;
import com.example.spring_member_crud.exception.CustomException;
import com.example.spring_member_crud.exception.ExceptionType;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // controller 앞단에서 발생하는 에러를 캐치하기 위함.
public class CustomExceptionHandler {
    // @ExceptionHandler 로 어떤 에러인지?를 명시하여 그에 해당하는 에러가 발생 시 바로 아래 정의된 메서드에서 처리를 한다.
    @ExceptionHandler // @ExceptionHandler(value = {UserNotFoundException.class, NullPointerException.class})
    public BaseResponse<Void> handle(CustomException e) {
        ExceptionType type = e.getType();
        log.atLevel(type.getLevel()).setCause(e).log(e.getMessage());
        return BaseResponse.failure(type);
    }

    @ExceptionHandler // @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<List<FieldErrorDto>> handle(MethodArgumentNotValidException e) {
        List<FieldErrorDto> errors = new ArrayList<>();
        StringBuilder messageBuilder = new StringBuilder();

        for (ObjectError each : e.getBindingResult().getAllErrors()) {
            FieldError eachError = (FieldError) each;
            messageBuilder.append(String.format("[%s = %s : %s] ", eachError.getField(), eachError.getRejectedValue(),
                eachError.getDefaultMessage()));
            errors.add(
                new FieldErrorDto(eachError.getField(), eachError.getRejectedValue(), eachError.getDefaultMessage()));
        }

        log.warn(messageBuilder.toString(), e);
        return BaseResponse.failure(ExceptionType.INVALID_INPUT, errors);
    }

    @ExceptionHandler
    public BaseResponse<Void> handle(Exception e) {
        log.error(e.getMessage(), e);
        return BaseResponse.failure(ExceptionType.UNCLASSIFIED_ERROR);
    }
}
