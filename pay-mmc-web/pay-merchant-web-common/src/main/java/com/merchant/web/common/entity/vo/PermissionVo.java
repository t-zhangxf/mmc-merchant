package com.merchant.web.common.entity.vo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String  merchantId;
    private Integer  attrId;
    private Integer  fatherId;
    private Integer  permissionType;
}
