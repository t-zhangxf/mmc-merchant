package com.merchant.web.entity;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbUserPermission {
    private Integer id;
    private String name;
    private String url;
    private Integer fatherId;
    private Integer sort;
    private Integer attrId;
    private Date createTime;
    private Date updateTime;
    private Boolean isDeleted;
    private String creatby;
    private String updateby;
    private Integer roleType;
    private String enName;
    private String psNumber;
}