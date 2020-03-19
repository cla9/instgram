package com.instagram.member.common.validation.annotation;

import com.instagram.member.common.validation.validator.UserNameDuplicationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserNameDuplicationValidator.class)
public @interface UniqueUserName {
    String message() default "Member name is Duplication";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
