package com.instagram.member.common.util.enumUtil;

import com.instagram.member.common.error.exception.EnumConverterException;
import com.instagram.member.common.util.CommonType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.EnumSet;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumValueConvertUtils {
    public static <T extends Enum<T> & CommonType> T ofCode(Class<T> enumClass, String code){
        if(StringUtils.isEmpty(code)) return null;
        return EnumSet.allOf(enumClass).stream()
                .filter(v -> v.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new EnumConverterException(String.format("enum=[%s], code[%s]가 존재하지 않습니다.", enumClass.getName(), code)));
    }

    public static <T extends Enum<T> & CommonType> String toCode(T enumValue){
        if(null == enumValue) return "";
        return enumValue.getCode();
    }
}
