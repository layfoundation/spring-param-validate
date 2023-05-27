package com.github.layfoundation.controller;

import com.github.layfoundation.base.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/health/check")
    public Result<String> healthCheck() {
        return Result.ok("hello world!!");
    }

}
