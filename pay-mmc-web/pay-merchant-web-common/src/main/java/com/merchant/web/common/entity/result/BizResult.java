package com.merchant.web.common.entity.result;
import com.merchant.web.common.enums.SystemEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class BizResult<T> implements Serializable {
    private String pageNum;
    private String pageSize;
    private String totalElements;
    private String bizCode;
    private String message;
    private T data;
    private String date;
    private static final long serialVersionUID = -7032284145697889716L;

    public static BizResult buildBizResult(String pageNum, String pageSize, String totalElements, String bizCode, String message, Object data) {
        return new BizResult(pageNum, pageSize, totalElements, bizCode, message, data);
    }
    public static BizResult buildBizResult(String bizCode, String message, Object data) {
        return new BizResult(bizCode, message, data);
    }
    public static BizResult buildBizResult(String bizCode, String message) {
        return new BizResult(bizCode, message);
    }
    public BizResult(String pageNum, String pageSize, String totalElements, String bizCode, String message, T data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.bizCode = bizCode;
        this.message = message;
        this.data = data;
        this.date=new Date().getTime()+"";
    }
    public BizResult(String pageNum , String bizCode, String message, T data) {
        this.pageNum = pageNum;
        this.bizCode = bizCode;
        this.message = message;
        this.data = data;
    }

    public BizResult(String bizCode, String message, T data) {
        this.bizCode = bizCode;
        this.message = message;
        this.data = data;
    }
    public BizResult(String bizCode, String message) {
        this.bizCode = bizCode;
        this.message = message;
    }
    public BizResult() {
    }
}
