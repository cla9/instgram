package com.instagram.member.model.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.instagram.member.common.util.serializer.SignUpRequestDeserializer;
import com.instagram.member.common.validation.annotation.UniqueEmail;
import com.instagram.member.model.Email;
import com.instagram.member.model.Name;
import com.instagram.member.model.Password;
import lombok.*;

import javax.validation.Valid;

public class SignUp {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @ToString
    @JsonDeserialize(using = SignUpRequestDeserializer.class)
    public static class SignUpRequestDTO {
        @Valid private Name name;
        @Valid private Password password;
        @Valid private Email email;
    }

    @Getter
    @Builder
    @ToString
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class SignUpResponseDTO {
        private String memberID;
    }
}
