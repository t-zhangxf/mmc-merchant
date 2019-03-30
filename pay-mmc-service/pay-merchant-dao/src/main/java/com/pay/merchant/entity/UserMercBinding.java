package com.pay.merchant.entity;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMercBinding {
    private Integer id;

    private String userNo;

    private Byte userType;

    private String mercId;

    private Date bindingDate;

    private Byte status;

    private String busCnl;

    private Date createTime;

    private String creator;

    private Date modifiedTime;

    private String modifier;

    private Byte isDeleted;
}