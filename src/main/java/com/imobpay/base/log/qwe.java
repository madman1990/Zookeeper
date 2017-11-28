package com.imobpay.base.log;

import java.math.BigDecimal;

public class qwe {

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("10");
        BigDecimal b = new BigDecimal("3");
        System.out.println(a.divide(b, 5, BigDecimal.ROUND_UP));
        System.out.println(a.divide(b, 5, BigDecimal.ROUND_DOWN));
        System.out.println(a.divide(b, 5, BigDecimal.ROUND_CEILING));
        System.out.println(a.divide(b, 5, BigDecimal.ROUND_FLOOR));
        System.out.println(a.divide(b, 5, BigDecimal.ROUND_HALF_UP));
        System.out.println(a.divide(b, 5, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(a.divide(b, 5, BigDecimal.ROUND_HALF_EVEN));
    }

}
