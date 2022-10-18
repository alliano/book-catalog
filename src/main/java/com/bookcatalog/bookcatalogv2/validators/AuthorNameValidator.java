package com.bookcatalog.bookcatalogv2.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.annotation.Configuration;

import com.bookcatalog.bookcatalogv2.validators.annotations.ValidAuthorName;

@Configuration
public class AuthorNameValidator implements ConstraintValidator<ValidAuthorName, java.lang.String>{

    @Override
    public boolean isValid(String authorName, ConstraintValidatorContext context) {
        return !authorName.equalsIgnoreCase("alliano");
    }

}
