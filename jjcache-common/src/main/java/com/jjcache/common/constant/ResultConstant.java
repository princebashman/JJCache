package com.jjcache.common.constant;

/**
 * @author jiangcx
 * @create 2021 - 07 - 07 - 15:00
 */
public class ResultConstant {

    public enum ResultEnum {
        SUCCESS_GENERAL(10000, "响应成功"),

        ERROR_GENERAL(20000, "未知异常");

        private int code;
        private String description;

        ResultEnum(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode () {
            return code;
        }

        public String getDescription () {
            return description;
        }
    }

}
