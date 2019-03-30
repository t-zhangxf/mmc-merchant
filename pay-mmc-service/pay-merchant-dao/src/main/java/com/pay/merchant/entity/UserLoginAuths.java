package com.pay.merchant.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginAuths {
    private Integer id;

    private String userNo;

    private Byte identityType;

    private String identifier;

    private String credential;

    private Byte verifiedFlag;

    private Byte internalFlag;

    private Date createTime;

    private String creator;

    private Date modifiedTime;

    private String modifier;

    private Byte isDeleted;

}