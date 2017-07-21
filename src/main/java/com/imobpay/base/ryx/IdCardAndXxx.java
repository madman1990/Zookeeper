/**
 *  <pre>	
 *  Project Name:Zookeeper .</br>
 *  File: IdCardAndXxx.java .</br>
 *  Package Name:com.imobpay.base .</br>
 *  Date      Author       Changes .</br>
 *  2017年5月15日   madman     Create  .</br>
 *  Description: .</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.ryx;

import com.alibaba.fastjson.JSONObject;
import com.imobpay.base.util.DateUtil;

/**
 * <pre>
 * 【类型】: IdCardAndXxx <br/> 
 * 【作用】: TODO ADD FUNCTION. <br/>  
 * 【时间】：2017年5月15日 下午5:10:00 <br/> 
 * 【作者】：madman <br/>
 * </pre>
 */
public class IdCardAndXxx {
    static final String reqUrl = "http://119.254.93.69:30333/authencrypt/0010";
    static final String code   = "QTUO0002";

    public static void main(String[] args) {
        try {
            JSONObject json = new JSONObject();
            json.put("SerialNo", System.currentTimeMillis() + "");
            json.put("TransDate", DateUtil.getCurrDate());
            json.put("TransTime", DateUtil.getCurrTime());
            json.put("BankNo", "6217680200192875");
            json.put("UserName", "彭正锋");
            json.put("CertNo", "430525199307084911");
            json.put("MobileNo", "13162328491");
            json.put("BankCode", "");
            byte[] reqByte = json.toString().getBytes("UTF-8");
            // String signData = new String(Base64.encodeBase64(CryptoUtil.digitalSign(plainBytes, hzfPriKey, "SHA1WithRSA")), charset);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
