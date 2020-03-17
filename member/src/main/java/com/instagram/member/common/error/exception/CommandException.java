package com.instagram.member.common.error.exception;


public class CommandException extends BusinessException {
    public CommandException(String message) {
        super(message, ErrorCode.COMMAND_ERROR);
    }
    public CommandException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
