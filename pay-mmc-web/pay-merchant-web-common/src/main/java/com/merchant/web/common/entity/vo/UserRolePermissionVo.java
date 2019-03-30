package com.merchant.web.common.entity.vo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRolePermissionVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String email;
    private String merchantId;
    private String userType;
    private String pageNum;
    private String pageSize;
}
