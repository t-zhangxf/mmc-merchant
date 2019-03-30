package com.merchant.web.common.entity.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class ResultVo implements Serializable {
    private String bizCode;
    private String message;
    private String date;
    public ResultVo() {
    }


    public ResultVo(String bizCode, String message) {
        this.bizCode = bizCode;
        this.message = message;
        this.date = new Date().getTime()+"";
    }
    public static ResultVo buildResult(String resultCode, String message) {
        ResultVo result = new ResultVo(resultCode, message);
        return result;
    }



    public static Result buildResult(String resultCode, String message, Object data) {
        Result result = new Result(resultCode, message, data);
        return result;
    }
}
