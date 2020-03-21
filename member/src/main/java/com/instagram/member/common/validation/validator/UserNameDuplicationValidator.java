package com.instagram.member.common.validation.validator;

import com.instagram.member.common.validation.annotation.UniqueUserName;
import com.instagram.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class UserNameDuplicationValidator implements ConstraintValidator<UniqueUserName, String> {
    private final MemberRepository memberRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !memberRepository.existsByUserName(name);
    }
}
