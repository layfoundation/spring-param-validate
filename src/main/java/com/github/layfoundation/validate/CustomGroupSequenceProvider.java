package com.github.layfoundation.validate;

import com.github.layfoundation.validate.group.Girl;
import com.github.layfoundation.vo.UserVo;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class CustomGroupSequenceProvider implements DefaultGroupSequenceProvider<UserVo> {

    @Override
    public List<Class<?>> getValidationGroups(UserVo user) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(UserVo.class);
        if (user != null) {
            String sex = user.getSex();
            if ("女".equals(sex)) {
                defaultGroupSequence.add(Girl.class);
            }
        }
        return defaultGroupSequence;
    }
}
