package com.jjcache.common.util;

import java.math.BigInteger;

/**
 * 缓存数学工具类
 * @author jiangcx
 * @create 2021 - 09 - 09 - 17:25
 */
public class MathUtils {

    public static long add(long a , long b){
        BigInteger biginta = new BigInteger(a + "");
        BigInteger bigintb = new BigInteger(b + "");
        return biginta.add(bigintb).longValue();
    }

    public static long subtract(long a , long b){
        BigInteger biginta = new BigInteger(a + "");
        BigInteger bigintb = new BigInteger(b + "");
        return biginta.subtract(bigintb).longValue();
    }

    public static long multiply(long a , long b){
        BigInteger biginta = new BigInteger(a + "");
        BigInteger bigintb = new BigInteger(b + "");
        return biginta.multiply(bigintb).longValue();
    }

    public static long divide(long a , long b){
        BigInteger biginta = new BigInteger(a + "");
        BigInteger bigintb = new BigInteger(b + "");
        return biginta.divide(bigintb).longValue();
    }


}
