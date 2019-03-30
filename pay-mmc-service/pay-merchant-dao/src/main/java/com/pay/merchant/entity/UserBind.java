package com.pay.merchant.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBind implements Serializable {
    private static final long serialVersionUID = 3026591685413490671L;
    private String userNo;
    private String email;
    private String identifier;
    private String creator;
    private String modifier;
    private String status;
    private String activeStatus;
    private String busCnl;
    private String userType;
    private String merChantId;
    private String roleNumber;
    private String merChantNull;
    private String roleId;
    private  Byte acStatus;
    private  Byte isDeleted;
}
