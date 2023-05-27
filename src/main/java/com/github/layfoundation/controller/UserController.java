package com.github.layfoundation.controller;

import com.github.layfoundation.base.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import com.github.layfoundation.vo.UserVo;

@Slf4j
@RestController
@Validated
public class UserController {
    @ApiOperation("保存用户")
    @PostMapping("/save/user")
    public Result<Boolean> saveUser(@RequestBody @Validated UserVo user) {
        return Result.ok();
    }

    @ApiOperation("查询用户")
    @GetMapping("/list/user")
    public Result<List<UserVo>> listUser(
            @Min(value = 100, message = "id不能小于100") @RequestParam("id") Long id,
            @NotBlank(message = "名称不能为空") @RequestParam("name") String name,
            @Max(value = 100, message = "年龄不能大于100") @RequestParam("age") Integer age) {
        List<UserVo> list = new ArrayList<>();
        return Result.ok(list);
    }

}
