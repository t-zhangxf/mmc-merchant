package com.merchant.web.common.entity.vo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionShowVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private  Integer type;
    private  String email;
    private  String merchantId;
    private  String permitStr;
    private  String userNo;
}
