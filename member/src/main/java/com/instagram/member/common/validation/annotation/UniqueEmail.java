package com.instagram.member.common.validation.annotation;

import com.instagram.member.common.validation.validator.EmailDuplicationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailDuplicationValidator.class)
public @interface UniqueEmail {
    String message() default "Email is Duplication";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
