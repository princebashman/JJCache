package com.jjcache.core.conf;

import com.jjcache.common.exception.CacheConfigExcepiton;
import com.jjcache.core.Jjcache;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author jiangcx
 * @create 2021 - 07 - 07 - 17:12
 */
public class JjCacheConfig {

   private Properties properties; // main 配置文件
   private Properties level1_CacheProperties = new Properties();
   private Properties level2_CacheProperties = new Properties();

   private Boolean openLevel2; // 是否开启二级缓存
   private Boolean stampeding; // 缓存雪崩解决方案
   private Boolean penetration; // 缓存穿透解决方案
   private Boolean cacheEmptyValue; // 是否缓存空对象
   private Boolean bloomFilter; // 布隆过滤器
   private Boolean simpleFilter; // 简单过滤器
   private String serialization; // 序列化格式

   /**
    * 从文件资源名称读取配置
    * @param configResource
    * @return
    * @throws IOException
    */
   public final static JjCacheConfig initConfig(String configResource) throws IOException {
      try (InputStream stream = getConfigStream(configResource)) {
         return initFromConfig(stream);
      }
   }

   /**
    * 获取对应资源文件输入流
    * @param resource
    * @return
    */
   public static InputStream getConfigStream(String resource) {
      InputStream configStream = Jjcache.class.getResourceAsStream(resource);
      if (configStream == null) {
         configStream = Jjcache.class.getClassLoader().getParent().getResourceAsStream(resource);
      }
      if (configStream == null) {
         throw new CacheConfigExcepiton("Cannot find " + resource + ".");
      }
      return configStream;
   }

   /**
    * 从文件资源读取配置
    * @param configFile
    * @return
    * @throws IOException
    */
   public final static JjCacheConfig initConfig(File configFile) throws IOException {
      try (FileInputStream stream = new FileInputStream(configFile)) {
         return initFromConfig(stream);
      }
   }

   /**
    * 从文件资源输入流中读取配置
    * @param stream
    * @return
    * @throws IOException
    */
   public static JjCacheConfig initFromConfig(InputStream stream) throws IOException {
      Properties properties = new Properties();
      properties.load(stream);
      return initFromConfig(properties);
   }



   public final static JjCacheConfig initFromConfig(Properties properties) {
      JjCacheConfig config = new JjCacheConfig();
      config.properties = properties;

      String serialization = trim(config.properties.getProperty("jjcache.serialization"));
      config.serialization = serialization  == null ? "json" : serialization;

      config.stampeding = "true".equalsIgnoreCase(trim(config.properties.getProperty("jjcache.processor.stampeding")));
      config.penetration = "true".equalsIgnoreCase(trim(config.properties.getProperty("jjcache.processor.penetration")));
      config.cacheEmptyValue = "true".equalsIgnoreCase(trim(config.properties.getProperty("jjcache.processor.penetration.cache_empty_value")));
      config.bloomFilter = "true".equalsIgnoreCase(trim(config.properties.getProperty("jjcache.processor.penetration.bloom_filter")));
      config.simpleFilter = "false".equalsIgnoreCase(trim(config.properties.getProperty("jjcache.processor.penetration.simple_filter")));
      config.openLevel2 = trim(config.properties.getProperty("jjcache.open_level2")) == null ? Boolean.TRUE : "true".equalsIgnoreCase(trim(config.properties.getProperty("jjcache.cache_empty_value")));

      postCheck();
      return config;
   }

   private static void postCheck() {
      // TODO 保证缓存穿透解决策略只有一个开启
   }

   public Boolean getCacheEmptyValue() {
      return cacheEmptyValue;
   }

   public Boolean getOpenLevel2() {
      return openLevel2;
   }

   public Boolean getStampeding() {
      return stampeding;
   }

   public Boolean getBloomFilter() {
      return bloomFilter;
   }

   public Boolean getSimpleFilter() {
      return simpleFilter;
   }

   public Boolean getPenetration() {
      return penetration;
   }

   private static String trim(String str) {
      return (str != null) ? str.trim() : null;
   }



}
