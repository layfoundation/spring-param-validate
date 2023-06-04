package com.github.layfoundation.controller;

import com.github.layfoundation.base.Result;
import com.github.layfoundation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @Autowired
    private UserService userService;

    /**
     * localhost:8080/validate/health/check
     *
     * @return
     */
    @GetMapping("/health/check")
    public Result<String> healthCheck() {
        return Result.ok("hello world!!");
    }

}
