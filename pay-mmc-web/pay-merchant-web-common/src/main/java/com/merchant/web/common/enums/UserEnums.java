package com.merchant.web.common.enums;

import lombok.Getter;

@Getter
public enum UserEnums {
    SYSTEM_SUCCESS("200", "login successfully"),
    USER_ACCOUNT_IS_NOT_EXITS("600", "user account is not exits"),
    USER_INCORRECT_CREDENTIALS("601", "username or password  incorrect "),
    USER_REDIRECT_PAGE("602"," user redirect page"),
    USER_HAS_NO_PERMISSION("603"," user had no permission"),
    USER_STATUS_IS_LOCKED("604", "user  is locked"),
    USER_EMAIL_IS_NOT_EXITS("605", "user email is not  exits or is not active"),
    USER_EMAIL_IS_NULL("606", "user email is null"),
    USER_PASSWORD_IS_NULL("607", "user password is null"),
    USER_RESET_PASSWORD_IS_ERROR("608", "user reset password is exception"),
    USER_RESET_EMAIL_URL_EFFECT("609", "user reset email  url is invalid"),
    USER_SEND_EMAIL_ERROR("610", "user send email  error"),
    USER_NEW_PASSWORD_IS_NULL("611", "user new  password  is null"),
    USER_ORI_PASSWORD_IS_NULL("612", "user original  password  is null"),
    USER_ORI_NEW_PASSWORD_IS_SAME("613", "user original and new password  is the same"),
    USER_ORI_PASSWORD_IS_ERROR("614", "the old password is incorrect"),
    USER_EMAIL_TOKEN_IS_NULL("615", "the email token is null"),
    USER_EMAIL_TOKEN_EFFECT("616", "the parameter 'Token' from the link is invalid"),
    USER_EMAIL_TOKEN_NORMAL("617", "user email token normal"),
    USER_EMAIL_TOKEN_EXPIRE("618", "user email token expire"),
    USER_RESPONSE_IS_ERROR("619", "user email response is error"),
    USER_PERMISSION_STR_IS_NULL("6190", "user permission str is null"),
    USER_TOKEN_IS_NULL("620", "token cannot be empty"),
    USER_NOT_ADD_MYSELF("621","the account to be added already exits under the current merchant"),
    USER_EMAIL_IS_UN_ACTIVE("622","user email is not  exits or  is un active"),
    USER_MERCHANT_NOT_THE_SAME_AS("623","current merchantId is is different from selected merchantId"),
    USER_EMAIL_IS_ACTIVE("624","user email   is actived"),
    USER_EMAIL_FORMAT_IS_ERROR("6191", "user email format is error"),
    USER_MERCHANT_HAD_ADDED("6192", "Repeated action error! The user has already been entitled to the merchant."),
    USER_NO_IS_NOT_NULL("6193", "userNo is null"),
    USER_INFO_IS_NOT_NULL("6194", "userInfo is null"),
    USER_MERCHANT_IS_NULL("6195", "user  merchant is null"),
    USER_EMAIL_IS_NOT_EQUAL("6196", "user  email is not the same as"),
    USER_PERMISSION_IS_ERROR("6197", "user permission is error"),
    USER_NOT_DELETE_MYSELF("6198", "One cannot edit the permission for himself"),
    USER_NOT_DELETE_ADMIN_PERMISSIONS("6199", "One cannot edit the permission of admin user.\n"),
    ;
    private String code;
    private String desc;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    UserEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
