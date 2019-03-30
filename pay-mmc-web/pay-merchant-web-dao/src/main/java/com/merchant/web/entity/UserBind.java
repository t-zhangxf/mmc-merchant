package com.merchant.web.entity;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private String busCnl;
    private String userType;
    private String merChantId;
    private Byte activeStatus;
    private List<Integer> bindStatus=new ArrayList<Integer>();
}
