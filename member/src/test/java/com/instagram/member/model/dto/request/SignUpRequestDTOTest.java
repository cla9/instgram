package com.instagram.member.model.dto.request;

import com.instagram.member.model.Email;
import com.instagram.member.model.Name;
import com.instagram.member.model.Password;
import com.instagram.member.model.dto.SignUp.SignUpRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class SignUpRequestDTOTest {
    @Autowired
    private Validator validator;

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

        assertThat(constraintViolations).isNotEmpty();
        assertThat(constraintViolations)
                .extracting(ConstraintViolation::getMessage)
                .containsOnly(
                        "활동명의 길이는 최소 1자 이상 최대 30자 이하입니다.",
                        "올바르지 않은 이메일 형식입니다.");

    }


}