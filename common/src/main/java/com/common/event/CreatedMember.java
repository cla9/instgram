package com.common.event;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class CreatedMember {
    private String memberID;
    private String memberName;
    private String password;
    private String memberFullName;
    private String memberEmail;
}
