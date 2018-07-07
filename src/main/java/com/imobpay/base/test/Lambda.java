//package com.imobpay.base.test;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.swing.JButton;
//
//public class Lambda {
//    public static void main(String[] args) {
//
//        // Java 8之前：
//        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
//        // for (String feature : features) {
//        // System.out.println(feature);
//        // }
//        // List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
//        // features.forEach(n -> System.out.println(n));
//        features.forEach(System.out::println);
//        // Java 8之前：
//        JButton show = new JButton("Show");
//        show.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Event handling without lambda expression is boring");
//            }
//        });
//        // Java 8方式：
//        show.addActionListener((e) -> {
//            System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
//        });
//
//        // new Thread(new Runnable() {
//        // @Override
//        // public void run() {
//        // System.out.println("Before Java8, too much code for too little to do");
//        // }
//        // }).start();
//        // Java 8方式：
//        // new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
//    }
//    // public void get() -> System.out.println("Hello Lambda Expressions");
//
//}
