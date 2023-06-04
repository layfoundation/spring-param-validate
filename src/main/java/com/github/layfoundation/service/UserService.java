package com.github.layfoundation.service;

import com.github.layfoundation.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;

@Service
@Slf4j
public class UserService {
    @Autowired
    private Validator validator;

    public boolean editUser(UserVo user) {
        Set<ConstraintViolation<UserVo>> validateSet = validator.validate(user);
        if (CollectionUtils.isNotEmpty(validateSet)) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<UserVo> violation : validateSet) {
                errorMessage.append("[").append(violation.getPropertyPath().toString()).append("]")
                        .append(violation.getMessage()).append(";");
            }
            throw new ValidationException(errorMessage.toString());
        }
        return Boolean.TRUE;
    }

    public UserVo getByName(String name) {
        return new UserVo();
    }
}
