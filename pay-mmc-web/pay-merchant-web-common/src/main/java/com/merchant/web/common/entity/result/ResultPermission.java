package com.merchant.web.common.entity.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class ResultPermission implements Serializable {
    private String bizCode;
    private String message;
    private Object data;
    private String date;
    public ResultPermission() {
    }
    public ResultPermission(String bizCode, String message) {
        this.bizCode = bizCode;
        this.message = message;
        this.date = new Date().getTime()+"";
    }
    public ResultPermission(String bizCode, String message, Object data) {
        this.bizCode = bizCode;
        this.message = message;
        this.data = data;
        this.date = new Date().getTime()+"";
    }
    public ResultPermission(String bizCode, String message, Object data,String userType) {
        this.bizCode = bizCode;
        this.message = message;
        this.data = data;
        this.date = new Date().getTime()+"";
    }
    public ResultPermission(String bizCode, String message, Object data, Date date,String userType) {
        this.bizCode = bizCode;
        this.message = message;
        this.data = data;
        this.date = date.getTime()+"";
    }
    public static Result buildResult(String resultCode, String message) {
        Result result = new Result(resultCode, message);
        return result;
    }
    public static ResultPermission buildResult(String resultCode, String message, Object data,String userType) {
        ResultPermission result = new ResultPermission(resultCode, message, data,userType);
        return result;
    }
    public static ResultPermission buildResult(String resultCode, String message, Object data, Date date,String userType) {
        ResultPermission result = new ResultPermission(resultCode, message, data, date,userType);
        return result;
    }
    public static ResultPermission buildSuccessResult(Object data) {
        ResultPermission result = new ResultPermission("200", "success", data);
        return result;
    }
}
