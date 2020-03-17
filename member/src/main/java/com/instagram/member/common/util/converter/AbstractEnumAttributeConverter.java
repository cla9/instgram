package com.instagram.member.common.util.converter;


import com.instagram.member.common.util.CommonType;
import com.instagram.member.common.util.enumUtil.EnumValueConvertUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;

@Getter
@AllArgsConstructor
public class AbstractEnumAttributeConverter<E extends Enum<E> & CommonType> implements AttributeConverter<E, String> {
    private Class<E> targetEnumClass;
    private boolean nullable;
    private String enumName;

    @Override
    public String convertToDatabaseColumn(E attribute) {
        if(!nullable && null == attribute)
            throw new IllegalArgumentException(String.format("%s(은)는 NULL로 저장할 수 없습니다.", enumName));
        return EnumValueConvertUtils.toCode(attribute);
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        if(!nullable && StringUtils.isEmpty(dbData))
            throw new IllegalArgumentException(String.format("%s(이)가 DB에 NULL 혹은 Empty로 (%s) 저장되어 있습니다."));
        return EnumValueConvertUtils.ofCode(targetEnumClass, dbData);
    }
}
