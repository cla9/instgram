package com.instagram.member.common.error.exception;


public class UserNameDuplicationException extends InvalidInputException {
    public UserNameDuplicationException(String message) {
        super(message,ErrorCode.USERNAME_DUPLICATION);
    }

    public UserNameDuplicationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
