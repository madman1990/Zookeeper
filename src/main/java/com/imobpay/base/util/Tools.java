package com.imobpay.base.util;

import java.util.Random;

/**
 * @ClassName: Tools
 * @Description: 工具类，处理补位，基础类型的转换，加解密，校验数据等等
 * @author Lance.Wu
 *
 *         Copyright: Copyright (c) 2014 Company:www.imobpay.com
 */
public class Tools {

    /**
     * rightFill(按总长度-已经有的长度来右补数据) (是通过字节形式的长度来得到长度的)
     *
     * @Title: rigntFill
     * @Description: 按补的位数，右补数据
     * @Date May 4, 2014 2:31:16 PM
     * @modifyDate May 4, 2014 2:31:16 PM
     * @param src
     *            需要补位的数据 java.lang.String
     * @param count
     *            需要的总长度
     * @param in
     *            需要补的内容 java.lang.String
     * @return String 左补之后的内容
     */
    public static String rightFill(String src, int count, String in) {
        if (count > 0) {
            if (src.getBytes().length < count) {
                int i;
                String fills = "";
                if (in == null || "".equals(in)) {
                    in = "0";
                }
                for (i = 0; i < count; i++) {
                    fills += in;
                }
                return src + fills.substring(0, fills.getBytes().length - src.getBytes().length);
            } else {
                return src;
            }
        } else {
            return src;
        }
    }

    /**
     * getOnlyPK(利用时间yyyyMMddHHmmssSSS 格式,然后补4为的随机数得到的唯一值)
     * 
     * @Title: getOnlyPK
     * @Description: 利用时间yyyyMMddHHmmssSSS 格式,然后补4为的随机数得到的唯一值
     * @Date May 4, 2014 2:58:50 PM
     * @modifyDate May 4, 2014 2:58:50 PM
     * @return String 得到的唯一值 java.lang.String
     */
    public static synchronized String getOnlyPK() {
        String timePK = DateUtil.getCurrDate("yyyyMMddHHmmssSSS");
        int min = 1000;
        int max = 9999;
        Random rand = new Random();
        int tmp = Math.abs(rand.nextInt());
        tmp = tmp % (max - min + 1) + min;
        return timePK + tmp;
    }

    /**
     * 替换指定字符串中的一部分
     * 
     * @param src
     *            要替换的字符
     * @param replaceNum
     *            替换的字符数
     * @param replaceStr
     *            替换字符
     * @return String
     */
    public static String replaceString(String src, int replaceNum, String replaceStr) {
        int startIndex = (src.length() - replaceNum) / 2;
        String startString = src.substring(0, startIndex);
        String replace = "";
        for (int i = 0; i < replaceNum; i++) {
            replace += replaceStr;
        }
        int endIndex = startIndex + replace.length();
        String endString = src.substring(endIndex);
        return startString + replace + endString;
    }

    /**
     * 按字节长度左补位
     * 
     * @param src
     *            src
     * @param count
     *            count
     * @param in
     *            in
     * @return String
     */
    public static String leftFill(String src, int count, String in) {
        if (count > 0) {
            if (src.getBytes().length < count) {
                String fills = "";
                if (in == null || "".equals(in)) {
                    in = "0";
                }
                for (int i = 0; i < count; i++) {
                    fills = fills + in;
                }

                return fills.substring(0, fills.getBytes().length - src.getBytes().length) + src;
            } else {
                return src;
            }
        } else {
            return src;
        }
    }

    /**
     * 序列
     */
    private static int sequence = 0;

    /**
     * 
     * @方法名称:getTrmSeqNum
     * @方法作用：
     * @方法条件：
     * @方法流程：
     * @作者:madman
     * @return 返回结果
     */
    public static synchronized String getTrmSeqNum() {
        sequence = sequence >= 999999 ? 1 : sequence + 1;
        String s = Integer.toString(sequence);
        return addLeftZero(s, 6);
    }

    /**
     * 
     * @方法名称:addLeftZero
     * @方法作用：
     * @方法条件：
     * @方法流程：
     * @作者:madman
     * @param s
     *            参数
     * @param length
     *            长度
     * @return 结果
     */
    public static String addLeftZero(String s, int length) {
        // StringBuilder sb=new StringBuilder();
        int old = s.length();
        if (length > old) {
            char[] c = new char[length];
            char[] x = s.toCharArray();
            if (x.length > length) {
                throw new IllegalArgumentException("Numeric value is larger than intended length: " + s + " LEN " + length);
            }
            int lim = c.length - x.length;
            for (int i = 0; i < lim; i++) {
                c[i] = '0';
            }
            System.arraycopy(x, 0, c, lim, x.length);
            return new String(c);
        }
        return s.substring(0, length);

    }

    /**
     * 对用户姓名进行格式化处理
     * 
     * @param originalName 将要格式化的名字
     * @return String
     */
    public static String handleCustomerName(String originalName) {
        String newName = "";
        int length = originalName.length();
        if (length > 1) {
            newName = originalName.substring(0, 1) + handleChar(length - 1);
        } else if (length == 1) {
            newName = originalName;
        }
        return newName;
    }

    /**
     * 
     * 星号添加
     *
     * @param length
     *            长度
     * 
     * @return String
     */
    private static String handleChar(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append("*");
        }
        return sb.toString();
    }
 
   
    
}