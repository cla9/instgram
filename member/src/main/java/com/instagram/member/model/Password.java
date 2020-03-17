package com.instagram.member.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = "password")
@Getter
public class Password implements Serializable {
    @Column(name = "password", length = 128)
    @NotEmpty
    @Length(min = 1, max = 128)
    private String password;
    public static Password of(String password){
        return new Password(password);
    }
}
