package com.imobpay.base.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class Pro {
    public static void main(String[] args) {
        Properties propertie = new Properties();
        try {
            FileInputStream inputFile = new FileInputStream("F:\\regExConfig.properties");
            FileReader fr = new FileReader(new File("F:\\regExConfig.properties"));
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer("");
            String str = null;
            while ((str = br.readLine()) != null) {
                System.out.println(str);

            }
            // propertie.load(inputFile);
            // inputFile.close();
            // Set<Object> keySet = propertie.keySet();
            // for (Object object : keySet) {
            // System.out.print(object + "--");
            // System.out.println(propertie.get(object));
            // }
            System.out.println("成功打飞机");
        } catch (FileNotFoundException ex) {
            System.out.println("读取属性文件--->失败！- 原因：文件路径错误或者文件不存在");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("装载文件--->失败!");
            ex.printStackTrace();
        }
    }
}
