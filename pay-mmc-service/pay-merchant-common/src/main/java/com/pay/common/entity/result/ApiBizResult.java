package com.pay.common.entity.result;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiBizResult<T> extends BizResult implements Serializable {
    private String pageSize;
    private String pageNum;
    private String totalElements;
    public static <T> ApiBizResult<T> buildData(String returnCode, String message, String pageSize, String pageNum, String totalElements, T t) {
        ApiBizResult<T> response = new ApiBizResult<T>();
        response.setPageNum(pageNum);
        response.setPageSize(pageSize);
        response.setTotalElements(totalElements);
        response.setBizCode(returnCode);
        response.setMessage(message);
        response.setData(t);
        return response;
    }
}
