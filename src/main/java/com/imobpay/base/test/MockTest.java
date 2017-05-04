package com.imobpay.base.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * <pre>
 * 【类型】: MockTest <br/> 
 * 【作用】: 测试 <br/>  
 * 【时间】：2017年3月7日 上午11:18:51 <br/> 
 * 【作者】：madman <br/>
 * </pre>
 */
public class MockTest {
    public static void main(String[] args) {

        String str1 = "1";
        String str2 = "1";
        System.out.println(str1 == str2);
        String str3 = new String("1");
        String str4 = new String("1");
        System.out.println(str3 == str4);
        System.out.println(str3.equals(str4));

        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("5");
        a.add("6");
        a.add("3");
        for (String temp : a) {
            if ("1".equals(temp)) {
                a.remove(temp);
                System.out.println("溢出数据了");
            }
        }
        System.out.println(a.toString());
        System.out.println(26 / 5);
        int[] orderBy = { 10, 5, 1, 13, 24, 55, 43, 12, 8, 7 };
        JSONObject json = new JSONObject();
        JSONArray arr = new JSONArray();
        JSONObject s1 = new JSONObject();
        s1.put("sex", "男");
        s1.put("name", "小A");
        JSONObject s2 = new JSONObject();
        s2.put("sex", "女");
        s2.put("name", "凤姐");
        arr.add(s1);
        arr.add(s2);
        json.put("teacher", "斌斌");
        json.put("stu", arr);
        System.out.println(json.toString());
    }
}
