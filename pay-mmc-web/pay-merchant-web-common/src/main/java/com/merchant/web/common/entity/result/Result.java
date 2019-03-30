package com.merchant.web.common.entity.result;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class Result  {

    private String bizCode;

    private String message;

    private Object data;
    private String date;

    public Result() {
    }


    public Result(String bizCode, String message) {
        this.bizCode = bizCode;
        this.message = message;
        this.date = new Date().getTime()+"";
    }

    public Result(String bizCode, String message,String isRegistered) {
        this.bizCode = bizCode;
        this.message = message;
        this.date = new Date().getTime()+"";
    }

    public Result(String bizCode, String message, Object data) {
        this.bizCode = bizCode;
        this.message = message;
        this.data = data;
        this.date = new Date().getTime()+"";
    }
    public static Result buildResult(String resultCode, String message) {
        Result result = new Result(resultCode, message);
        return result;
    }
    public static Result buildResult(String resultCode, String message,Object data) {
        Result result = new Result();
        result.setBizCode(resultCode);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
    public static Result buildSuccessResult(Object data) {
        Result result = new Result("200", "success", data);
        return result;
    }

}
