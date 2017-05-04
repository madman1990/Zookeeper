/**
 *  <pre>	
 *  Project Name:Zookeeper .</br>
 *  File: Log4jTest.java .</br>
 *  Package Name:com.imobpay.base.test .</br>
 *  Date      Author       Changes .</br>
 *  2017年4月5日   madman     Create  .</br>
 *  Description: .</br>
 *  Copyright 2014-2015 YINGXIANG FINANCE Services Co.,Ltd. All rights reserved..</br>
 *  <pre>	
 */
package com.imobpay.base.test;

import org.apache.log4j.Logger;
import org.springframework.util.Log4jConfigurer;

/**
 * <pre>
 * 【类型】: Log4jTest <br/> 
 * 【作用】: TODO ADD FUNCTION. <br/>  
 * 【时间】：2017年4月5日 下午6:17:30 <br/> 
 * 【作者】：madman <br/>
 * </pre>
 */
public class Log4jTest {
    static {
        try {
            Log4jConfigurer.initLogging("F:\\log4j.properties");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Cannot Initialize log4j");
        }
    }
    static Logger log = Logger.getLogger(Log4jTest.class);

    /**
     * 【方法名】 : (这里用一句话描述这个方法的作用). <br/>
     * 【注意】: (这里描述这个方法的注意事项 – 可选).<br/>
     * 【作者】: madman .<br/>
     * 【时间】： 2017年4月5日 下午6:17:30 .<br/>
     * 【参数】： .<br/>
     * 
     * @param args
     *            .<br/>
     *            <p>
     *            修改记录.<br/>
     *            修改人: madman 修改描述：创建新新件 .<br/>
     *            <p/>
     */
    public static void main(String[] args) {
        log.info("呵呵哈哈哈测试 ");
    }

}
