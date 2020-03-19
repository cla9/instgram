package com.instagram.member.common.validation.validator;

import com.instagram.member.common.validation.annotation.UniqueEmail;
import com.instagram.member.model.Email;
import com.instagram.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class EmailDuplicationValidator implements ConstraintValidator<UniqueEmail, Email> {
    private final MemberRepository memberRepository;

    @Override
    public boolean isValid(Email value, ConstraintValidatorContext context) {
        return !memberRepository.existsByEmail(value);
    }
}
