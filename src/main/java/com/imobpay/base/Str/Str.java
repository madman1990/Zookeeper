/**
 *  <pre>	
 *  Project Name:Zookeeper .</br>
 *  File: Str.java .</br>
 *  Package Name:com.imobpay.base.St .</br>
 *  Date      Author       Changes .</br>
 *  2017年5月10日   madman     Create  .</br>
 *  Description: .</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.Str;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * <pre>
 * 【类型】: Str <br/> 
 * 【作用】: TODO ADD FUNCTION. <br/>  
 * 【时间】：2017年5月10日 下午12:59:44 <br/> 
 * 【作者】：madman <br/>
 * </pre>
 */
public class Str {
    public static void main(String[] args) {
        int n = 3;
        int m = 3;

        System.out.println(n == m);

        String str = new String("hello");
        String str1 = new String("hello");
        String str2 = new String("hello");

        System.out.println(str1 == str2);

        str1 = str;
        str2 = str;
        System.out.println(str1 == str2);

        String st1 = "彭";
        String st2 = "彭";
        System.out.println(st1 == st2);
        System.out.println(st1.equals(st2));
        String st3 = new String("彭");
        String st4 = new String("彭");
        System.out.println(st3 == st4);
        System.out.println(st3.equals(st4));

    }

    private void setTest() {

        Set<String> sets = new TreeSet();
        Set<String> sets2 = new HashSet<String>();
        sets2.add("123");
        sets2.add("123");
        sets2.add("123");

    }
}
