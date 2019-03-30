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
public class TbLoginLog implements Serializable {
    private static final long serialVersionUID = 3026591685413490671L;
    private Integer id;
    private String userNo;
    private String currentLoginIp;
    private Date createTime;
    private Date modifiedTime;
    private Integer isDeleted;
    private String requestUrl;
}