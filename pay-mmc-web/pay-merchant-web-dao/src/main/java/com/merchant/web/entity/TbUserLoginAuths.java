package com.merchant.web.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbUserLoginAuths implements Serializable {
    private static final long serialVersionUID = 3026591685413490671L;
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