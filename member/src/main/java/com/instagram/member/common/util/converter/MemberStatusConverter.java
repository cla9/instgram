package com.instagram.member.common.util.converter;

import com.instagram.member.model.aggregate.MemberStatus;

public class MemberStatusConverter extends AbstractEnumAttributeConverter<MemberStatus> {
    public static final String ENUM_NAME = "회원상태";
    public MemberStatusConverter(){ super(MemberStatus.class,false, ENUM_NAME); }
}
