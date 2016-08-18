package com.imobpay.base.util;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * ClassName: Des3Util <br/>
 * Reason: Des3Util <br/>
 * date: 2016年7月28日 下午5:12:19 <br/>
 * 
 * @author madman
 * @version
 * @since JDK 1.6 PayIFramework 1.0
 */
public class Des3Util {

    /***
     * 
     * Base64Encode:base64编码. <br/>
     * 
     * @author madman
     * @param b
     *            加密数据
     * @return
     * @throws Exception
     *             异常
     * @return 返回结果：byte[]
     * @since JDK 1.6 PayUrlShort 1.0
     */
    public static byte[] Base64Encode(byte[] b) throws Exception {
        return Base64.encodeBase64(b);
    }

    /**
     * 
     * Base64Decode:解密. <br/>
     * 
     * @author madman
     * @param b
     *            解密数据
     * @return
     * @throws Exception
     *             异常
     * @return 返回结果：byte[]
     * @since JDK 1.6 PayUrlShort 1.0
     */
    public static byte[] Base64Decode(byte[] b) throws Exception {
        return Base64.decodeBase64(b);
    }

    /***
     * 
     * IvGenerator:初始化盐值. <br/>
     * 
     * @author madman
     * @param b
     *            盐值
     * @return
     * @throws Exception
     *             异常
     * @return 返回结果：IvParameterSpec
     * @since JDK 1.6 PayUrlShort 1.0
     */
    private static IvParameterSpec IvGenerator(byte[] b) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(b);
        return iv;
    }

    /***
     * 
     * KeyGenerator:初始化密钥. <br/>
     * 
     * @author madman
     * @param keyStr
     *            參數
     * @return
     * @throws Exception
     *             异常
     * @return 返回结果：Key
     * @since JDK 1.6 PayUrlShort 1.0
     */
    private static Key KeyGenerator(String keyStr) throws Exception {
        DESedeKeySpec keySpec = new DESedeKeySpec(keyStr.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(keyAlgorithm);
        return keyFactory.generateSecret(keySpec);
    }

    /***
     * 
     * IVGenerator:hex编码. <br/>
     * 
     * @author madman
     * @param strIV
     *            参数
     * @return
     * @throws Exception
     *             异常
     * @return 返回结果：byte[]
     * @since JDK 1.6 PayUrlShort 1.0
     */
    public static byte[] IVGenerator(String strIV) throws Exception {
        return Hex.decodeHex(strIV.toCharArray());
    }

    /***
     * 
     * GenerateDigest:SHA1加密. <br/>
     * 
     * @author madman
     * @param strTobeDigest
     *            参数
     * @return
     * @throws Exception
     *             异常
     * @return 返回结果：String
     * @since JDK 1.6 PayUrlShort 1.0
     */
    public static String GenerateDigest(String strTobeDigest) throws Exception {
        byte[] input = strTobeDigest.getBytes(codingType);
        byte[] output = (byte[]) null;
        MessageDigest digestGenerator = MessageDigest.getInstance(digestAlgorithm);
        digestGenerator.update(input);
        output = digestGenerator.digest();
        return new String(Base64Encode(output), codingType);
    }

    /***
     * 
     * Encrypt:(3DES加密). <br/>
     * 
     * @author madman
     * @param strTobeEnCrypted
     *            加密传
     * @param strKey
     *            密钥
     * @param byteIV
     *            盐值
     * @return
     * @throws Exception
     *             异常
     * @return 返回结果：String
     * @since JDK 1.6 PayUrlShort 1.0
     */
    public static String Encrypt(String strTobeEnCrypted, String strKey, byte[] byteIV) throws Exception {
        byte[] input = strTobeEnCrypted.getBytes(codingType);
        Key k = KeyGenerator(strKey);
        IvParameterSpec iVSpec = byteIV.length != 0 ? IvGenerator(byteIV) : IvGenerator(defaultIV);
        Cipher c = Cipher.getInstance(cryptAlgorithm);
        c.init(1, k, iVSpec);
        byte[] output = c.doFinal(input);
        return new String(Base64Encode(output), codingType);
    }

    /***
     * 
     * Decrypt:(3DES解密). <br/>
     * 
     * @author madman
     * @param strTobeDeCrypted
     *            解密串
     * @param strKey
     *            密钥
     * @param byteIV
     *            盐值
     * @return
     * @throws Exception
     *             异常
     * @return 返回结果：String
     * @since JDK 1.6 PayUrlShort 1.0
     */
    public static String Decrypt(String strTobeDeCrypted, String strKey, byte[] byteIV) throws Exception {
        byte[] input = Base64Decode(strTobeDeCrypted.getBytes(codingType));
        Key k = KeyGenerator(strKey);
        IvParameterSpec iVSpec = byteIV.length != 0 ? IvGenerator(byteIV) : IvGenerator(defaultIV);
        Cipher c = Cipher.getInstance(cryptAlgorithm);
        c.init(2, k, iVSpec);
        byte[] output = c.doFinal(input);
        return new String(output, codingType);
    }

    /***
     * HexStringToByteArray
     * 
     * @author madman
     * @param s
     *            十六进制
     * @return
     * @return 返回结果：byte[]
     * @since JDK 1.6 PayUrlShort 1.0
     */
    public static byte[] HexStringToByteArray(String s) {
        byte[] buf = new byte[s.length() / 2];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) (chr2hex(s.substring(i * 2, i * 2 + 1)) * 16 + chr2hex(s.substring(i * 2 + 1, i * 2 + 1 + 1)));
        }

        return buf;
    }

    /****
     * 
     * chr2hex:(hex转字节). <br/>
     * 
     * @author madman
     * @param chr 參數
     * @return
     * @return 返回结果：byte
     * @since JDK 1.6 PayUrlShort 1.0
     */
    private static byte chr2hex(String chr) {
        if ("0".equals(chr)) {
            return 0;
        }
        if ("1".equals(chr)) {
            return 1;
        }
        if ("2".equals(chr)) {
            return 2;
        }
        if ("3".equals(chr)) {
            return 3;
        }
        if ("4".equals(chr)) {
            return 4;
        }
        if ("5".equals(chr)) {
            return 5;
        }
        if ("6".equals(chr)) {
            return 6;
        }
        if ("7".equals(chr)) {
            return 7;
        }
        if ("8".equals(chr)) {
            return 8;
        }
        if ("9".equals(chr)) {
            return 9;
        }
        if ("A".equals(chr)) {
            return 10;
        }
        if ("B".equals(chr)) {
            return 11;
        }
        if ("C".equals(chr)) {
            return 12;
        }
        if ("D".equals(chr)) {
            return 13;
        }
        if ("E".equals(chr)) {
            return 14;
        }
        return (byte) (!"F".equals(chr) ? 0 : 15);
    }

    /** 参数 */
    private static String codingType      = "UTF-8";
    /** 参数 */
    private static String digestAlgorithm = "SHA1";
    /** 参数 */
    private static String cryptAlgorithm  = "DESede/CBC/PKCS5Padding";
    /** 参数 */
    private static String keyAlgorithm    = "DESede";
    /** 参数 */
    private static byte[] defaultIV       = "12345678".getBytes();

    /**
     * 
     * 方法名： getcodingType.<br/>
     *
     * 创建者：Lance.Wu.<br/>
     * 创建日期：2016年1月15日.<br/>
     * 创建时间：下午3:36:06.<br/>
     * 参数者异常：@return .<br/>
     * 其它内容： JDK 1.6 QtServerAPI 1.0.<br/>
     */
    public static String getcodingType() {
        return codingType;
    }

    /**
     * 
     * 方法名： setcodingType.<br/>
     *
     * 创建者：Lance.Wu.<br/>
     * 创建日期：2016年1月15日.<br/>
     * 创建时间：下午3:36:14.<br/>
     * 参数者异常：@param codingType .<br/>
     * 其它内容： JDK 1.6 QtServerAPI 1.0.<br/>
     */
    public static void setcodingType(String codingType) {
        Des3Util.codingType = codingType;
    }

    /**
     * 
     * main:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author madman
     * @param args
     *            f
     * @since JDK 1.6 PayUrlShort 1.0
     */
    public static void main(String[] args) {
        try {
            JSONObject j = new JSONObject();
            j.put("A", "123");
            byte[] byteIV = new byte[] {};
            String e = Des3Util.Encrypt(j.toString(), "Yixingyixingyixing123456", byteIV);
            System.out.println(e);
            String d = Des3Util.Decrypt(e, "Yixingyixingyixing123456", byteIV);
            System.out.println(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
