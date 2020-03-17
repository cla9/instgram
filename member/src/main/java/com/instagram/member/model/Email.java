package com.instagram.member.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = "email")
public class Email implements Serializable {
    @NotEmpty
    @javax.validation.constraints.Email
    private String email;

    public static Email of(String email){
        return new Email(email);
    }
    public String getEmailAddress(){
        return this.email;
    }
}
