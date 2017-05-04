package com.imobpay.base.test;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class JVMTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Properties sp = System.getProperties();
        Enumeration e = sp.propertyNames();// 返回属性列表中所有键的枚举
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            System.out.println(key + "=" + sp.getProperty(key));
        }
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("notepad.exe TestProperties.java");// TestProperties.java文件要放在TestProperties文件夹根目录下
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        p.destroy();

        // TODO Auto-generated method stub
        // Properties sp = System.getProperties();
        // Enumeration e = sp.propertyNames();// 返回属性列表中所有键的枚举
        // while (e.hasMoreElements()) {
        // String key = (String) e.nextElement();
        // System.out.println(key + "=" + sp.getProperty(key));
        // }
    }

}
