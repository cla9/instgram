package com.instagram.member.common.error.exception;

public class InvalidInputException extends BusinessException{
    public InvalidInputException(String message){
        super(message, ErrorCode.INVALID_INPUT_VALUE);
    }
    public InvalidInputException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
