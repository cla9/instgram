package com.instagram.member.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.instagram.member.common.validation.annotation.UniqueEmail;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = "email")
@UniqueEmail(message = "{email.duplication}")
public class Email implements Serializable {
    @Getter
    @NotEmpty(message = "{email.empty}")
    @javax.validation.constraints.Email(message = "{email.invalid.format}")
    private String email;

    public static Email of(String email){
        return new Email(email);
    }
    @JsonIgnore
    public String getEmailAddress(){
        return this.email;
    }
}
