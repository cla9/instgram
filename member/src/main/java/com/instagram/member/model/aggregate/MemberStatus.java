package com.instagram.member.model.aggregate;

import com.instagram.member.common.util.CommonType;
import lombok.Getter;

@Getter
public enum  MemberStatus implements CommonType {
    PENDING("생성","S001"),
    ACTIVATED("활성화","S002");

    private String desc;
    private String code;

    MemberStatus(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }
}
