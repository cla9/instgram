package com.instagram.member.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.instagram.member.common.validation.annotation.UniqueUserName;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString(of = {"userName","fullName"})
@Validated
@Embeddable
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Name implements Serializable {
    @NotEmpty(message = "{name.empty}")
    @Length(min = 1, max = 30, message = "{name.length}")
    @UniqueUserName(message = "{name.duplication}")
    private String userName;

    @NotEmpty(message = "{fullname.empty}")
    @Length(min = 1, max = 255, message = "{fullname.length}")
    private String fullName;
}
