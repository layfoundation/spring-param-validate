package com.github.layfoundation.service;

import com.github.layfoundation.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    public UserVo getByName(String name) {
        return new UserVo();
    }
}
