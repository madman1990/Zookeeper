package com.imobpay.base.util;

import java.security.MessageDigest;
import java.util.Random;

import com.imobpay.base.log.Log;

/**
 * 
 * ClassName: ShortUrl <br/>
 * Function: 短链工具类 <br/>
 * date: 2016年7月27日 下午1:59:14 <br/>
 * 
 * @author madman
 * @version
 * @since JDK 1.6 PayUrlShort 1.0
 */
public class ShortUrlUtil {
    /**
     * 
     * main:测试类 <br/>
     * 
     * @author madman
     * @param args
     *            參數
     * @since JDK 1.6 PayUrlShort 1.0
     */
    public static void main(String[] args) {
        String sLongUrl = "http://www.51bi.com/bbs/_t_278433840/"; // 原始链接
        System.out.println("长链接:" + sLongUrl);
        // 将产生4组6位字符串
        String[] aResult = shortUrl(sLongUrl);
        // 打印出结果
        for (int i = 0; i < aResult.length; i++) {
            System.out.println("[" + i + "]:" + aResult[i]);
        }
        Random random = new Random();
        // 产成4以内随机数
        int j = random.nextInt(4);
        // 随机取一个作为短链
        System.out.println("短链接:" + aResult[j]);
    }

    /**
     * 
     * getShortUrl:根据长连接获取短连接. <br/>
     * 
     * @author madman
     * @param urlLong
     *            长连接
     * @return 返回短连接
     * @since JDK 1.6 PayUrlShort 1.0
     */
    public static String getShortUrl(String urlLong) {
        // 将产生4组6位字符串
        String[] aResult = shortUrl(urlLong);
        // 打印出结果
        Random random = new Random();
        // 产成4以内随机数
        int j = random.nextInt(4);
        // 随机取一个作为短链
        return aResult[j];
    }

    /**
     * 
     * shortUrl:(这里用一句话描述这个方法的作用). <br/>
     * 
     * @author madman
     * @param url
     *            参数
     * @return
     * @return 返回结果：String[]
     * @since JDK 1.6 PayUrlShort 1.0
     */
    public static String[] shortUrl(String url) {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY
        String key = "test";
        // 要使用生成 URL 的字符
        String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"

        };

        // 对传入网址进行 MD5 加密
        String hex = md5ByHex(key + url);

        String[] resUrl = new String[4];
        for (int i = 0; i < 4; i++) {

            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);

            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 ,
            // 如果不用long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            for (int j = 0; j < 6; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加
                outChars += chars[(int) index];
                // 每次循环按位右移 5 位
                lHexLong = lHexLong >> 5;
            }
            // 把字符串存入对应索引的输出数组
            resUrl[i] = outChars;
        }
        return resUrl;
    }

    /**
     * 
     * md5ByHex:(这里用一句话描述这个方法的作用). <br/>
     * 
     * @author madman
     * @param src
     *            f
     * @return
     * @return 返回结果：String
     * @since JDK 1.6 PayUrlShort 1.0
     */
    public static String md5ByHex(String src) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = src.getBytes();
            md.reset();
            md.update(b);
            byte[] hash = md.digest();
            String hs = "";
            String stmp = "";
            for (int i = 0; i < hash.length; i++) {
                stmp = Integer.toHexString(hash[i] & 0xFF);
                if (1 == stmp.length()) {
                    hs = hs + "0" + stmp;
                } else {
                    hs = hs + stmp;
                }
            }
            return hs.toUpperCase();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            return "";
        }
    }

}
