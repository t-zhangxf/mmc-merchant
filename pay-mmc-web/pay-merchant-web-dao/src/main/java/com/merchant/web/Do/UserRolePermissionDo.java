package com.merchant.web.Do;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRolePermissionDo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String  createTime;
    private Date    startDate;
    private String  email;
    private String  permitStr;
    private String  roleType;
    private String  userNo;
    private String  userStatus;
}
