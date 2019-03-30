package com.merchant.web.common.utils;

public class StringUtils {
    public static boolean emailFormat(String email) {
        boolean tag = true;
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            tag = false;
        }
        return tag;
    }

    public static void main(String[] args) {
        System.out.println(emailFormat("1717@11.com"));
    }
}
