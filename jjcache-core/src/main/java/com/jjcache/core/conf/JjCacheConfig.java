package com.jjcache.core.conf;

import com.jjcache.common.exception.CacheConfigExcepiton;
import com.jjcache.core.Jjcache;

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

   private Boolean cacheEmptyValue; // 是否缓存空对象
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

      config.serialization = trim(config.properties.getProperty("jjcache.serialization")) == null ? "json" : trim(config.properties.getProperty("jjcache.serialization"));
      config.cacheEmptyValue = "true".equalsIgnoreCase(trim(config.properties.getProperty("jjcache.cache_empty_value")));

      return config;
   }

   public Boolean getCacheEmptyValue() {
      return cacheEmptyValue;
   }

   private static String trim(String str) {
      return (str != null) ? str.trim() : null;
   }



}
