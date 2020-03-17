package com.instagram.member.common.error.exception;


public class EmailDuplicationException extends InvalidInputException {
    public EmailDuplicationException(String message) {
        super(message, ErrorCode.EMAIL_DUPLICATION);
    }
    public EmailDuplicationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
