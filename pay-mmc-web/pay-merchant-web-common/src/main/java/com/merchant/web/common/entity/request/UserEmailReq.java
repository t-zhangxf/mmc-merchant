package com.merchant.web.common.entity.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEmailReq implements Serializable {
    private  String email;
    private  String merchantId;
    private  String userNo;
}
