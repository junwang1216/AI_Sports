package com.sports.common.exception;

import com.sports.common.util.HttpStatusCode;
import org.apache.commons.lang3.StringUtils;

public class PlatformException extends RuntimeException {

    // 错误码规范, xxxx 四位数字表示模块 + xxx 三位错误码

    public static PlatformException SESSION_EXPIRE = new PlatformException(1000001, "会话过期");
    public static PlatformException INTERNAL_ERROR = new PlatformException(1000002, "操作失败,请稍后重试");
    public static PlatformException INTERNAL_TIMEOUT = new PlatformException(1000003, "操作超时");

    public static PlatformException GLOBAL_NEED_LOGIN = new PlatformException(1000401, "您尚未登录");

    private int status;
    private String message;
    private String desc;

    public PlatformException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public PlatformException(int status, String message, String desc) {
        this.status = status;
        this.message = message;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        if (this.status == HttpStatusCode.InternalServerError) {
            return StringUtils.isBlank(message) ? "服务器内部错误，请稍后重试。" : message;
        }
        return message;
    }

    public PlatformException create(Object... args) {
        return new PlatformException(this.status, String.format(this.message, args));
    }
}
