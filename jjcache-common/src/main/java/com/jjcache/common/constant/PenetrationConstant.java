package com.jjcache.common.constant;

/**
 * 缓存穿透解决方案 - 枚举
 * @author jiangcx
 * @create 2021 - 09 - 18 - 11:00
 */
public class PenetrationConstant {

    public enum PenetrationEnum {
        EMPTY_VALUE(1, "emptyValue", "缓存空值"),
        SIMPLE_FILTER(2, "filterCheck", "过滤校验"),
        BLOOM_FILTER(3, "emptyValue", "布隆过滤器");

        private Integer id;
        private String name;
        private String desc;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

        /**
         * 获取策略数量
         * @return
         */
        public static Integer getCount() {
            return values().length;
        }

        PenetrationEnum(Integer id, String name, String desc) {
            this.id = id;
            this.name = name;
            this.desc = desc;
        }
    }

}
