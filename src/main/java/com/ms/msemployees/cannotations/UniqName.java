package com.ms.msemployees.cannotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ms.msemployees.validation.validators.UniqNameValidator;

@Documented
@Constraint(validatedBy=UniqNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqName {

	String message() default "{name.uniq}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	
}
