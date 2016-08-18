package com.imobpay.base.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.imobpay.base.log.Log;

/**
 * 
 * ClassName: HttpUtil <br/>
 * date: 2016年8月16日 下午3:53:42 <br/>
 * 
 * @author madman
 * @version
 * @since JDK 1.6 PayUrlShort 1.0
 */
public class HttpUtil {

    /**
     * 
     * send:(这里用一句话描述这个方法的作用). <br/>
     * 
     * @author madman
     * @param callURL 地址
     * @param postData 数据
     * @return
     * @return 返回结果：String
     * @since JDK 1.6 PayUrlShort 1.0
     */
    public static String send(String callURL, String postData) {
        Log.info("call url is:" + callURL);
        Log.info("call postData is:" + postData);
        DataOutputStream out = null;
        HttpURLConnection connection = null;
        BufferedReader data = null;
        InputStream in = null;
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(callURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30000);
            // 设置从主机获取数据超时时间
            connection.setReadTimeout(30000);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("xx", "ddddddddddddddddd");
            connection.connect();
            out = new DataOutputStream(connection.getOutputStream());
            out.write(postData.getBytes("UTF-8"));
            out.flush();
            int rc = connection.getResponseCode();
            Log.info("connect result is:" + rc);
            // 响应成功
            if (rc == 200) {
                String temp;
                in = connection.getInputStream();
                data = new BufferedReader(new InputStreamReader(in, "utf-8"));
                while ((temp = data.readLine()) != null) {
                    result.append(temp);
                    temp = null;
                }
            }

        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        } finally {
            try {
                if (null != data) {
                    data.close();
                    Log.info("data已关闭");
                }
            } catch (Exception e2) {
                Log.error(e2.getMessage(), e2);
            }
            try {
                if (null != in) {
                    in.close();
                    Log.info("in已关闭");

                }
            } catch (Exception e2) {
                Log.error(e2.getMessage(), e2);
            }
            try {
                if (null != out) {
                    out.close();
                    Log.info("out已关闭");
                }
            } catch (IOException e) {
                Log.error(e.getMessage(), e);
            }
            try {
                if (null != connection) {
                    connection.disconnect();
                    Log.info("connection已关闭");
                }
            } catch (Exception e2) {
                Log.error(e2.getMessage(), e2);
            }
        }
        Log.info("returnData is:" + result.toString());
        return result.toString();
    }

}