package com.merchant.web.common.entity.result;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
@Setter
@Getter
public class ResultLogin<T> implements Serializable {
    private String bizCode;
    private String message;
    private String token;
    private String date;
    private T data;
    public ResultLogin() {
    }
    public ResultLogin(String bizCode, String message) {
        this.bizCode = bizCode;
        this.message = message;
        this.date = new Date().getTime()+"";
    }
    public ResultLogin(String bizCode, String message,String token) {
        this.bizCode = bizCode;
        this.message = message;
        this.token=token;
        this.date = new Date().getTime()+"";
    }
    public static ResultLogin buildResult(String bizCode, String message,String token,Object data) {
        ResultLogin result = new ResultLogin(bizCode, message,token);
        result.setData(data);
        return result;
    }
}
