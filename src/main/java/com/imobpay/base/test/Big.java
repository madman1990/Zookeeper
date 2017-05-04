/**
 *  <pre>	
 *  Project Name:Zookeeper .</br>
 *  File: Big.java .</br>
 *  Package Name:com.imobpay.base.test .</br>
 *  Date      Author       Changes .</br>
 *  2017年4月12日   madman     Create  .</br>
 *  Description: .</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.test;

import java.math.BigDecimal;

/**
 * <pre>
 * 【类型】: Big <br/> 
 * 【作用】: TODO ADD FUNCTION. <br/>  
 * 【时间】：2017年4月12日 下午8:57:52 <br/> 
 * 【作者】：madman <br/>
 * </pre>
 */
public class Big {
    public static void main(String[] args) {
        String f = "231.11";
        BigDecimal bb = new BigDecimal(f);
        BigDecimal setScale = bb.setScale(2, BigDecimal.ROUND_UP);
        System.out.println(setScale);

        BigDecimal b = new BigDecimal(0.51);
        System.out.println(b.doubleValue());
        System.out.println(b);
        BigDecimal c = new BigDecimal("0.51");
        System.out.println(c);
    }
}
