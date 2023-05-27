package com.github.layfoundation.base;


public enum ApiCode {

    /**
     * 操作成功
     **/
    SUCCESS(200, "操作成功"),
    /**
     * 非法访问
     **/
    UNAUTHORIZED(401, "没有权限"),
    /**
     * 没有权限
     **/
    NOT_PERMISSION(403, "非法访问"),
    /**
     * 你请求的资源不存在
     **/
    NOT_FOUND(404, "你请求的资源不存在"),
    /**
     * 操作失败
     **/
    FAIL(500, "操作失败"),
    /**
     * 登录授权异常
     **/
    AUTHENTICATION_EXCEPTION(5104, "登录授权异常"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION(5108, "METHOD NOT SUPPORTED"),

    ;

    private final int code;
    private final String message;

    ApiCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiCode getApiCode(int code) {
        ApiCode[] ecs = ApiCode.values();
        for (ApiCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
