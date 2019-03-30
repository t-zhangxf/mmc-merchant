package com.merchant.web.common.entity.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseDataResp<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bizCode;
    private String message;
    private BaseData data;
    private String date;
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BaseData {
       private String  isRegistered;
    }
}
