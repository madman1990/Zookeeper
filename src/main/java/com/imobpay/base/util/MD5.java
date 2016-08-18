package com.imobpay.base.util;

import java.security.MessageDigest;

import com.imobpay.base.log.Log;

/**
 * 
 * @项目名称:CheckServer
 * @文件名称:MD5.java
 * @包名:com.imobpay.util
 * @时间:2015年12月16日上午11:24:37
 * @工作邮箱：madman@imobpay.com
 * @类名:MD5 MD5算法工具类
 * @功能描述:
 * @时间:2015年12月16日 上午11:24:37
 * @作者:madman
 * @JDK版本:JDK 1.6 CheckServer 1.0
 */
public class MD5 {
    /**
     * 
     * @方法名称:md5
     * @方法作用：
     * @方法条件：
     * @方法流程：
     * @作者:madman MD5工具算法类
     * @param s
     *            参数
     * @return 返回结果
     */
    public static final String md5(String s) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] strTemp = s.getBytes("UTF-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 
     * @方法名称:md52
     * @方法作用：MD5算法2
     * @方法条件：
     * @方法流程：
     * @作者:madman
     * @param b
     *            参数
     * @return 返回结果
     */
    public static final String md52(byte[] b) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] strTemp = b;
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @author eric 20140410 带指定密钥串的MD5加密,高阳捷迅游戏，Q币使用，其他渠道使用依据实际情况
     * @param strSrc strSrc
     * @param key key
     *            <pre>
     * 字符串形式的md5加密，以小写密文串
     * </pre>
     * @return String
     */
    public static String md5(String strSrc, String key) {
        String result = "";
        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes("UTF-8"));
            byte[] temp;
            temp = md5.digest(key.getBytes("UTF-8"));
            for (int i = 0; i < temp.length; i++) {
                result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
            }
        } catch (Exception e) {
            Log.error("Md5生成错误："+e.getMessage(), e);
        }
        return result;
    }

}
