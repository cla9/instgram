package com.instagram.member.common.error;

import com.instagram.member.common.error.exception.EmailDuplicationException;
import com.instagram.member.common.error.exception.ErrorCode;
import com.instagram.member.common.error.exception.UserNameDuplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.axonserver.connector.command.AxonServerRemoteCommandHandlingException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;
import java.util.concurrent.TimeoutException;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ApiExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult(), messageSource, Locale.getDefault());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ErrorResponse> handleTimeOutException(TimeoutException e){
        log.error("handleTimeOutException", e);
        return getErrorResponseResponseEntity(ErrorCode.TIMEOUT_ERROR);
    }

    private ResponseEntity<ErrorResponse> getErrorResponseResponseEntity(ErrorCode errorCode) {
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler(AxonServerRemoteCommandHandlingException.class)
    public ResponseEntity<ErrorResponse> handleAxonServerException(AxonServerRemoteCommandHandlingException e){
        log.error("handleAxonServerConstraintViolationException", e);
        return getErrorResponseResponseEntity(ErrorCode.COMMAND_ERROR);
    }

    @ExceptionHandler(EmailDuplicationException.class)
    public ResponseEntity<ErrorResponse> handleEmailDuplicationException(EmailDuplicationException e){
        log.error("handleEmailDuplicationException", e);
        return getErrorResponseResponseEntity(ErrorCode.EMAIL_DUPLICATION);
    }

    @ExceptionHandler(UserNameDuplicationException.class)
    public ResponseEntity<ErrorResponse> handleUserNameDuplicationException(UserNameDuplicationException e){
        log.error("handleUserNameDuplicationException", e);
        return getErrorResponseResponseEntity(ErrorCode.USERNAME_DUPLICATION);
    }
}
