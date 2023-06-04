package com.github.layfoundation.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.HibernateValidator;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;

public class ValidationUtils {
    private static final Validator VALIDATOR = Validation
            .byProvider(HibernateValidator.class)
            .configure()
            .failFast(true) //快速失败
            .buildValidatorFactory()
            .getValidator();

    public static <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolationSet = VALIDATOR.validate(obj);
        if (CollectionUtils.isNotEmpty(constraintViolationSet)) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<T> violation : constraintViolationSet) {
                errorMessage.append("[").append(violation.getPropertyPath().toString()).append("]")
                        .append(violation.getMessage()).append(";");
            }
            throw new ValidationException(errorMessage.toString());
        }
    }
}
