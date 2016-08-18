package com.imobpay.base.util;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @author cpjl 项目格式化工具包
 * 
 */
public class Format {
    /**
     * 获取类日志对象
     */
    private static Logger log = LoggerFactory.getLogger(Format.class);

    /**
     * 
     * 方法名： getOnlyPK.<br/>
     *
     * 创建者：madman.<br/>
     * 创建日期：2016年4月5日.<br/>
     * 创建时间：下午6:55:10.<br/>
     * 参数者异常：@return .<br/>
     * 其它内容： JDK 1.6 qtpay_note 1.0.<br/>
     */
    public static synchronized String getOnlyPK() {
        String timePK = dateTimeSss();
        int min = 1000;
        int max = 9999;
        Random rand = new Random();
        int tmp = Math.abs(rand.nextInt());
        tmp = tmp % (max - min + 1) + min;
        return timePK + tmp;
    }

    /**
     * 校验传入时间 与当前时间是否超过五分钟
     * 
     *
     * 传入字符串类型的数据
     * 
     * @param date
     *            日期
     * @return boolean
     */

    public static boolean checkTime(String date) {
        boolean flag = false;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date1 = null;
        Date date2 = new Date();
        try {
            date1 = format.parse(date);
        } catch (ParseException e) {
            log.info(e.getMessage(), e);
            log.error(e.getMessage(), e);
        }
        // 计算当前时间是否和传入时间大于5分钟
        long minutes = (date2.getTime() - date1.getTime()) / (1000 * 60);
        if (minutes > 5) {
            flag = true;
        }
        return flag;
    }

    /**
     * 根据当前时间信息生成 图片对应的URL信息
     * 
     * 例：
     * 
     * @return String 生成后的URL
     */

    public static String getPicUrl() {
        // 定义时间、返回字符串
        String hours = null;
        String picString = null;
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE); // 日
        int month = cal.get(Calendar.MONTH) + 1; // 月
        int year = cal.get(Calendar.YEAR); // 年
        int hour = cal.get(Calendar.HOUR_OF_DAY); // 时

        if (hour >= 0 && hour < 2) {
            hours = "00_02";
        } else if (hour >= 2 && hour < 4) {
            hours = "02_04";
        } else if (hour >= 4 && hour < 6) {
            hours = "04_06";
        } else if (hour >= 6 && hour < 8) {
            hours = "06_08";
        } else if (hour >= 8 && hour < 10) {
            hours = "08_10";
        } else if (hour >= 10 && hour <= 12) {
            hours = "10_12";
        } else if (hour >= 12 && hour < 14) {
            hours = "12_14";
        } else if (hour >= 14 && hour < 16) {
            hours = "14_16";
        } else if (hour >= 16 && hour < 18) {
            hours = "16_18";
        } else if (hour >= 18 && hour < 20) {
            hours = "18_20";
        } else if (hour >= 20 && hour < 22) {
            hours = "20_22";
        } else if (hour >= 22 && hour < 24) {
            hours = "22_24";
        }
        // 最终部分图片url
        picString = year + "/" + month + "/" + day + "/" + hours + "/";

        return picString;
    }

    /**
     * 根据传入的日期字符串和现在的当前时间作比较， 大于5秒提示银行处理中。
     * 
     *
     * @param date
     *            传入日期参数
     * @param param
     *            传入时间限制 单位 秒
     * 
     * @return String
     */

    public static boolean comparingTime(String date, int param) {
        boolean flag = false;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date1 = null;
        try {
            date1 = df.parse(date);
        } catch (ParseException e) {
            log.info(e.getMessage(), e);
            log.error(e.getMessage(), e);
        }
        Date date2 = new Date();
        long time = (date2.getTime() - date1.getTime() + 1000 * 5) / 1000;
        /** 如果大于5秒 代表银行处理中 */
        if (time > param) {
            flag = true;
        }

        return flag;
    }

    /**
     * 根据传入的日期字符串和现在的当前时间作比较， 大于5秒提示银行已受理。
     * 
     *
     * @param date
     *            传入日期参数
     * 
     * @return String
     */

    public static boolean comparingTimess(String date) {
        boolean flag = false;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date1 = null;
        try {
            date1 = df.parse(date);
        } catch (ParseException e) {
            log.info(e.getMessage(), e);
            log.error(e.getMessage(), e);
        }
        Date date2 = new Date();
        long time = (date2.getTime() - date1.getTime() + 1000 * 5) / 1000;
        /** 如果大于10秒 代表银行处理中 */
        if (time > 120) {
            flag = true;
        }

        return flag;
    }

    /**
     * 根据传入的日期字符串和现在的当前时间作比较， 增加5秒返回。
     * 
     *
     * @param date
     *            传入日期参数
     * 
     * @return String
     */

    public static String comparingTimeses(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        try {
            date1 = df.parse(date);
        } catch (ParseException e) {
            log.info(e.getMessage(), e);
            log.error(e.getMessage(), e);
        }
        long time = date1.getTime() + 1000 * 5;
        String times = df2.format(time).toString();

        return times;
    }

    /**
     * 将当前 时间加两个小时返回
     * 
     *
     * 传入字符串类型的数据
     * 
     * @return String
     */

    @SuppressWarnings("static-access")
    public static String timeadd() {
        Date date = null;
        String time = null;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.HOUR_OF_DAY, 2);
        date = calendar.getTime();
        time = format.format(date);

        return time;
    }

    /**
     * 比较当前月和日是否与传入的月日一致
     * 
     * @param time
     *            传入字符串类型的数据
     * @return String
     */

    public static boolean timeEqual(String time) {
        boolean flag = false;
        Date da = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MMdd");

        if (time.equals(df.format(da))) {
            flag = true;
        }

        return flag;
    }

    /**
     * 数据格式化
     * 
     * @param str
     *            传入字符串类型的数据
     * @return String
     */
    public static String formatDecimal(String str) {
        if (str == null) {
            str = "0.00";
            return str;
        }
        return String.valueOf(Double.parseDouble(str));
    }

    /**
     * 
     * 对传入的字符串日期进行格式化
     * 
     *
     * @param date
     *            传入字符串类型的日期
     * @throws Exception
     *             NullPointerException
     * @return String
     */
    public static String dateTimeFormat(String date) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date d = df.parse(date);
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return df2.format(d);
    }

    /**
     * 
     * 对传入的字符串日期进行格式化
     * 
     *
     * @param date
     *            传入字符串类型的日期
     * @throws Exception
     *             NullPointerException
     * @return String
     */
    public static String dateFormat(String date) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date d = df.parse(date);
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
        return df2.format(d);
    }

    /**
     * 
     * 对传入的字符串时间进行格式化
     * 
     *
     * @param date
     *            传入字符串类型的时间
     * @throws Exception
     *             NullPointerException
     * @return String
     */
    public static String timeFormat(String date) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("HHmmss");
        Date d = df.parse(date);
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
        return df2.format(d);
    }

    /**
     * 
     * 对传入的字符串日期进行格式化
     * 
     *
     * @param date
     *            传入字符串类型的日期
     * @throws Exception
     *             NullPointerException
     * @return String
     */
    public static String datesFormat(String date) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date d = df.parse(date);
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        return df2.format(d);
    }

    /**
     * 
     * 根据传入的日期字符串和 当前时间比较
     * 
     * 如果传入时间小于等于当前时间返回真 否则返回假
     * 
     * @param date
     *            传入字符串类型的日期
     * @throws Exception
     *             NullPointerException
     * @return String
     */
    public static boolean compareDate(String date) throws Exception {
        boolean flag = false;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date dd = df.parse(time());
        Date d = df.parse(date);
        if (dd.compareTo(d) == -1) {
            flag = true;
        }
        return flag;
    }

    /**
     * 
     * 取系统下一个月
     * 
     *
     * NullPointerException
     * 
     * @return String
     */
    public static String getAfterMonth() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, +1);
        date = calendar.getTime();
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMM");
        String dateBefore = sd.format(date);
        return dateBefore;
    }

    /**
     * 取系统下N天
     *
     *
     * @param values
     *            整形数据
     * @return
     * 
     * @return String
     */
    public static String getAfterDay(Integer values) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +values);
        date = calendar.getTime();
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        String dateBefore = sd.format(date);
        return dateBefore;
    }

    /**
     * string类型当前日期
     * 
     * @return String
     */
    public static String formatDate() {
        String sdate = time();
        return sdate.substring(0, 8);
    }

    /**
     * String类型当前时间6位
     * 
     * @return String
     */
    public static String formatTime() {
        String sdate = time();
        return sdate.substring(8, 14);
    }

    /**
     * 获取当前时间的前一天
     * 
     * @return String
     */
    public static String formateTime2() {
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
        String sdate = sd.format(new Date(date.getTime() - 1 * 24 * 60 * 60 * 1000));
        return sdate.substring(0, 8);
    }

    /**
     * 获取传入时间的后一天
     * 
     * @param date
     *            字符串日期
     * @return String
     * 
     */
    public static String formateDate2(String date) {

        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        Date dates = null;
        String sdate = null;
        try {
            dates = sd.parse(date);

            sdate = sd.format(new Date(dates.getTime() + 1 * 24 * 60 * 60 * 1000));
        } catch (ParseException e) {
            log.info(e.getMessage(), e);
            log.error(e.getMessage(), e);
        }

        return sdate;
    }

    /**
     * String类型当前时间14位
     * 
     * @return String
     */
    public static String time() {
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
        String sdate = sd.format(date);
        return sdate;
    }

    /**
     * 
     * 方法名： dateTimeSss.<br/>
     *
     * 创建者：madman.<br/>
     * 创建日期：2016年4月5日.<br/>
     * 创建时间：下午6:54:48.<br/>
     * 参数者异常：@return .<br/>
     * 其它内容： JDK 1.6 qtpay_note 1.0.<br/>
     */
    public static String dateTimeSss() {
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String sdate = sd.format(date);
        return sdate;
    }

    /**
     * 传入日期 减去当前时间 返回时间秒数
     * 
     * @param date
     *            日期字符串
     * 
     * @return String
     * @throws ParseException
     *             转换异常
     */
    public static String second(String date) throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
        Date sdate = sd.parse(date);
        String dates = time();
        Date sdates = sd.parse(dates);
        long second = new Date(sdate.getTime() - sdates.getTime()).getTime() / 1000;
        String seconds = String.valueOf(second);
        return seconds;
    }

    /**
     * String类型当前日期8位
     * 
     * @return String
     */
    public static String dateStr() {
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        String sdate = sd.format(date);
        return sdate;
    }

    /**
     * String类型当前时间14位
     * 
     * @return String
     */
    public static String time2() {
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sdate = sd.format(date);
        return sdate;
    }

    /**
     * 16进制转为2进制
     * 
     * @param hexString
     *            16进制字符串
     * @return String
     */
    public static String hexString2binaryString(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0) {
            return null;
        }

        String bString = "";
        String tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }

    /**
     * 轻解罗裳 独上兰舟 花自飘零水自流 英雄化剑依 唱一曲别离 谁在君怀里 昨日相依 今夜又相离 一出悲戏 终离佳人老矣 笑谈千年悲喜
     * 16进制数转为字节数组
     * 
     * @param hexString
     *            16进制数
     * @return 字节数组
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || "".equals(hexString)) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * byte数组转换成16进制字符串
     * 
     * @param src
     *            传入字节数组
     * @return String
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * byte数组转换成16进制字符数组
     * 
     * @param src
     *            传入字节数组
     * @return String
     */
    public static String[] bytesToHexStrings(byte[] src) {
        if (src == null || src.length <= 0) {
            return null;
        }
        String[] str = new String[src.length];

        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                str[i] = "0";
            }
            str[i] = hv;
        }
        return str;
    }

    /**
     * byte数组转换成16进制字符数组
     * 
     * @param c
     *            传入字fu
     * @return byte
     */
    private static byte charToByte(char c) {

        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * md5加密(16进制转字节数组md5加密)
     * 
     * @param b
     *            字节数组
     * @return String
     */
    public static final String md5(byte[] b) {
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
     * 分转换为元
     * 
     * @param fen
     *            字符串
     * @return String
     */
    public static String fromFenToYuan(String fen) {
        String yuan = "";
        // 左去0
        int mULTIPLIER = 100;
        Pattern pattern = Pattern.compile("^[1-9][0-9]*{1}");
        Matcher matcher = pattern.matcher(fen);
        if (matcher.matches()) {
            yuan = new BigDecimal(fen).divide(new BigDecimal(mULTIPLIER)).setScale(2).toString();
        }
        return yuan;
    }

    /**
     * base64字符转为byte数组
     * 
     * @param baseString
     *            base64字符
     * @return byte[]
     */
    public static byte[] decryptBASE64(String baseString) {
        if (baseString == null || "".equals(baseString)) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] by = decoder.decodeBuffer(baseString);
            for (int i = 0; i < by.length; ++i) {
                if (by[i] < 0) {// 调整异常数据
                    by[i] += 256;
                }
            }
            return by;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 字节数组转base64
     * 
     * @param parme
     *            字节数组
     * @return String
     */
    public static String encoderBASE64(byte[] parme) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(parme);

    }



}
