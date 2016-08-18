/**
 *  Project Name:PayUrlShort
 *  File: TestZip.java
 *  Package Name:com.imobpay.base.util
 *  Date      Author       Changes
 *  2016年8月4日   madman     Create
 *  Description:
 *  Copyright 2014-2015 QIANTUO FINANCE Services Co.,Ltd. All rights reserved.
 */
package com.imobpay.base.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

/**
 * ClassName: TestZip <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年8月4日 下午1:53:04 <br/>
 * 
 * @author madman
 * @version
 * @since JDK 1.6 PayUrlShort 1.0
 */
public class TestZip {
    /**
     * 
     * main:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author madman
     * @param args
     *            d
     * @since JDK 1.6 PayUrlShort 1.0
     */
    public static void main(String[] args) {
        try {
            File f = new File("D:\\123.zip");
            FileInputStream fin = new FileInputStream(f);
            byte[] byteArray = IOUtils.toByteArray(fin);
            // base64编码
            String encodeBase64String = Base64.encodeBase64String(byteArray);
            // base64解码
            @SuppressWarnings("unused")
            byte[] decodeBase64 = Base64.decodeBase64(encodeBase64String);
            FtpUtil.FtpPic("192.168.1.7", 21, "filesys", "filesys", "imobpay/take/", encodeBase64String, "12.zip");
            System.out.println("zip文件上传成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
