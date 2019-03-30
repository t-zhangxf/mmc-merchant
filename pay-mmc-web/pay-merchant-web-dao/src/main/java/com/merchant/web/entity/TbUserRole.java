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
public class TbUserRole implements Serializable {
    private static final long serialVersionUID = 3026591685413490671L;
    private Integer id;
    private String userNo;
    private String mercId;
    private Integer roleId;
    private Integer isDeleted;
    private Date createTime;
    private Date updateTime;
}