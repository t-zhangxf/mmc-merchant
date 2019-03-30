package com.merchant.web.vo;

import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleVo implements Serializable {
    private  static final long serialVersionUID = 3026591685413490671L;
    private  String  userNo;
    private  String  mercId;
    private Integer roleId;
}
