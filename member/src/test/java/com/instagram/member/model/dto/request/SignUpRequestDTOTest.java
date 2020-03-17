package com.instagram.member.model.dto.request;

import com.instagram.member.model.Email;
import com.instagram.member.model.Name;
import com.instagram.member.model.Password;
import com.instagram.member.model.dto.SignUp.SignUpRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

class SignUpRequestDTOTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void SignUpRequestDTOTest() {
        SignUpRequestDTO signUpRequestDTO = SignUpRequestDTO.builder()
                .name(Name.builder()
                        .userName("testtesttesttesttesttesttesttesttesttesttest")
                        .fullName("test")
                        .build())
                .password(Password.of("test"))
                .email(Email.of("test"))
                .build();

        Set<ConstraintViolation<SignUpRequestDTO>> constraintViolations = validator.validate(signUpRequestDTO);

        Assertions.assertThat(constraintViolations)
                .extracting(ConstraintViolation::getMessage)
                .containsOnly(
                        "반드시 최소값  1과(와) 최대값 30 사이의 길이이어야 합니다.",
                        "이메일 주소가 유효하지 않습니다.");

    }
}