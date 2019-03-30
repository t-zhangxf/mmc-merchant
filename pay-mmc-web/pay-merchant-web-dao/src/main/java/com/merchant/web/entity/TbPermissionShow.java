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
public class TbPermissionShow implements Serializable {
    private static final long serialVersionUID = 3026591685413490671L;
    private Integer id;
    private String  psNumber;
    private Integer isDeleted;
    private Date    createdTime;
    private Date    updatedTime;
    private String  type;
    private String  name;
    private String  fatherIds;
    private String  childIds;
}