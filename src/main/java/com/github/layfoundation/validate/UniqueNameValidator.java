package com.github.layfoundation.validate;

import com.github.layfoundation.service.UserService;
import com.github.layfoundation.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(name)) {
            return true;
        }
        UserVo user = userService.getByName(name);
        if (user == null) {
            return true;
        }
        return false;
    }
}
