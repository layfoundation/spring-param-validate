package com.github.layfoundation.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -504027247149928390L;

    /**
     * 响应码
     */
    private int code;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    public Result() {
        time = new Date();
    }

    public static Result<Boolean> result(boolean flag) {
        if (flag) {
            return ok();
        }
        return fail();
    }

    public static Result<Boolean> result(ApiCode apiCode) {
        return result(apiCode, null);
    }

    public static <T> Result<T> result(ApiCode apiCode, T data) {
        return result(apiCode, null, data);
    }

    public static <T> Result<T> result(ApiCode apiCode, String message, T data) {
        boolean success = false;
        if (apiCode.getCode() == ApiCode.SUCCESS.getCode()) {
            success = true;
        }
        String apiMessage = apiCode.getMessage();
        if (StringUtils.isBlank(message)) {
            message = apiMessage;
        }
        return (Result<T>) Result.builder()
                .code(apiCode.getCode())
                .message(message)
                .data(data)
                .success(success)
                .time(new Date())
                .build();
    }

    public static Result<Boolean> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        return result(ApiCode.SUCCESS, data);
    }

    public static <T> Result<T> ok(T data, String message) {
        return result(ApiCode.SUCCESS, message, data);
    }

    public static Result<Map<String, Object>> okMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>(1);
        map.put(key, value);
        return ok(map);
    }

    public static Result<Boolean> fail(ApiCode apiCode) {
        return result(apiCode, null);
    }

    public static Result<String> fail(String message) {
        return result(ApiCode.FAIL, message, null);

    }

    public static <T> Result<T> fail(ApiCode apiCode, T data) {
        if (ApiCode.SUCCESS == apiCode) {
            throw new RuntimeException("失败结果状态码不能为" + ApiCode.SUCCESS.getCode());
        }
        return result(apiCode, data);

    }

    public static <T> Result<T> fail(ApiCode apiCode, String msg) {
        if (ApiCode.SUCCESS == apiCode) {
            throw new RuntimeException("失败结果状态码不能为" + ApiCode.SUCCESS.getCode());
        }
        return result(apiCode, msg, null);

    }

    public static Result<String> fail(Integer errorCode, String message) {
        return new Result<String>()
                .setSuccess(false)
                .setCode(errorCode)
                .setMessage(message);
    }


    public static Result fail(String msg, Object value) {
        return result(ApiCode.FAIL, msg, value);
    }

    public static Result<Boolean> fail() {
        return fail(ApiCode.FAIL);
    }
}
