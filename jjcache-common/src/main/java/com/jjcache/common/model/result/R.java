package com.jjcache.common.model.result;

import com.jjcache.common.constant.ResultConstant;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 响应信息主体类
 *
 * @author jiangcx
 * @create 2021 - 07 - 07 - 14:54
 */

@Data
@ToString
public class R implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态标识 成功为1；失败为0
     */
    private int status;
    /**
     * 错误状态码 根据枚举对应映射内容
     */
    private int code;
    /**
     * 响应文本内容
     */
    private String msg;

    /**
     * 结果集
     */
    private HashMap<String, Object> data;

    {
        data = new HashMap<>();
    }

    public static R ok() {
        return ok(ResultConstant.ResultEnum.SUCCESS_GENERAL.getCode());
    }

    public static R ok(String msg) {
        return ok(ResultConstant.ResultEnum.SUCCESS_GENERAL.getCode(), msg);
    }

    public static R ok(int code) {
        return ok(code, null);
    }

    public static R ok(int code, String msg) {
        R r = new R();
        r.status = 1;
        r.code = code;
        r.msg = msg;
        return r;
    }

    public static R error() {
        return ok(ResultConstant.ResultEnum.ERROR_GENERAL.getCode());
    }

    public static R error(String msg) {
        return ok(ResultConstant.ResultEnum.ERROR_GENERAL.getCode(), msg);
    }

    public static R error(int code) {
        return ok(code, "未知异常");
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.status = 0;
        r.code = code;
        r.msg = msg;
        return r;
    }

    public R put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public int getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }



}
