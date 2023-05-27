package com.github.layfoundation.exception;

import com.github.layfoundation.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return Result.fail("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result runtimeException(RuntimeException e) {
        log.error("运行时异常:", e);
        return Result.fail("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail("服务器错误，请联系管理员", e);
    }

    /**
     * 方法参数无效异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result exceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errMsg = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errMsg.append(fieldError.getDefaultMessage()).append(";");
        }
        return Result.fail(errMsg.toString());
    }

    /**
     * bind异常
     */
    @ExceptionHandler(value = BindException.class)
    public Result validationExceptionHandler(BindException e) {
        log.error(e.getMessage(), e);
        BindingResult bindingResult = e.getBindingResult();
        String errorMesssage = "";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + "!";
        }
        return Result.fail(errorMesssage);
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result constraintViolationExceptionHandler(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessageTemplate());
        }
        return Result.fail(String.join(",", msgList));
    }

}
