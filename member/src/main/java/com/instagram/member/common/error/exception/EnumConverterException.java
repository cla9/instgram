package com.instagram.member.common.error.exception;


public class EnumConverterException extends BusinessException{
    public EnumConverterException(String message){
        super(message, ErrorCode.ENUM_CONVERTER_ERROR);
    }
    public EnumConverterException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
