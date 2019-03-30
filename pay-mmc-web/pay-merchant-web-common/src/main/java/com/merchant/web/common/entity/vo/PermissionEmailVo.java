package com.merchant.web.common.entity.vo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionEmailVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private  String email;
    private  String merchantId;
    private  String userNo;
}
