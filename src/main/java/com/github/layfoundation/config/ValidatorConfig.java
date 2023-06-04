package com.github.layfoundation.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidatorConfig {
    @Bean
    public Validator validator() {
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                // 快速失败模式
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }
}
