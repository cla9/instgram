package com.instagram.member.common.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.axoniq.axonserver.grpc.ErrorMessage;

import java.util.function.Supplier;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),
    TIMEOUT_ERROR(400, "C007", "Timeout Occured"),

    // Member
    EMAIL_DUPLICATION(400, "M001", "Email is Duplication"),
    LOGIN_INPUT_INVALID(400, "M002", "Login input is invalid"),
    USERNAME_DUPLICATION(400, "M003", "Username is Duplication"),
    COMMAND_ERROR(400, "M004", "Command Error"),
    ENUM_CONVERTER_ERROR(400, "M005", "Converting Process Error");


    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }
    public String getCode() {
        return code;
    }
    public int getStatus() {
        return status;
    }
}

