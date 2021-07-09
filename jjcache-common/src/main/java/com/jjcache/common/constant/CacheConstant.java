package com.jjcache.common.constant;

/**
 * @author jiangcx
 * @create 2021 - 07 - 07 - 17:19
 */
public class CacheConstant {

    public static final Integer CACHE_LEVEL_ONE = 1; // level_1
    public static final Integer CACHE_LEVEL_TWO = 2; // level_2

    public enum CacheTypeEnum {
        CACHE_TYPE_STRING(0, "String"), CACHE_TYPE_OBJECT(1, "Object"),
        CACHE_TYPE_NUMBER(2, "Number"), CACHE_TYPE_LIST(3, "List"),
        CACHE_TYPE_MAP(4, "Map"), CACHE_TYPE_SET(5, "Set"),
        CACHE_TYPE_NULL(6, "Null");

        Integer type;
        String description;

        CacheTypeEnum(Integer type, String  description) {
            this.type = type;
            this.description = description;
        }

        public Integer getType() {
            return type;
        }

        public String getDescription() {
            return description;
        }

        /**
         * 根据参数类型获取参数字段
         * @param type
         * @return
         */
        public static String getType(Integer type) {
            CacheTypeEnum[] cacheTypeEnums = values();
            for (CacheTypeEnum cacheTypeEnum : cacheTypeEnums) {
                if (cacheTypeEnum.getType().equals(type)) {
                    return cacheTypeEnum.getDescription();
                }
            }
            return null;
        }
    }
}
