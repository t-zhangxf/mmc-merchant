package com.pay.merchant.entity;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionShow {
    private Integer id;

    private String psNumber;

    private Integer isDeleted;

    private Date createdTime;

    private Date updatedTime;

    private Integer type;

    private String name;

    private String fatherIds;

    private String childIds;
    private String psId;
}