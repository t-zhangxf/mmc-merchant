package com.pay.common.utils;

import com.dominicsayers.isemail.IsEMail;
import com.dominicsayers.isemail.IsEMailResult;


public class CheckEmailUtil {

    public static boolean checkEmail(String email) throws Exception {
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            return false;
        }
        IsEMailResult result = IsEMail.is_email_verbose(email, true);
        System.out.println(result);
        switch (result.getState()) {
            case OK:
                return true;
            default:
                return false;
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println(checkEmail("lianghl_sh@26.com"));
    }
}