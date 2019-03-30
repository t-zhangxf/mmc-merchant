package com.merchant.web.entity;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private Integer id;
    private String userNo;
    private String nickName;
    private String avatar;
    private String email;
    private String mobile;
    private Byte userStatus;
    private Byte failureNum;
    private Date unlockTime;
    private Date createTime;
    private String creator;
    private Date modifiedTime;
    private String salt;
    private String modifier;
    private Byte isDeleted;
    private Byte activeStatus;
}