package com.bookcatalog.bookcatalogv2.validators.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.bookcatalog.bookcatalogv2.validators.AuthorNameValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = AuthorNameValidator.class)
public @interface ValidAuthorName {

    String message() default "invalid author name";

    Class<? extends Payload>[] payload() default{};

    Class<?>[] groups() default {};
}
