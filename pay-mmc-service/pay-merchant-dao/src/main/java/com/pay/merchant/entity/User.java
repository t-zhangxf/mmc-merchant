package com.pay.merchant.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 3026591685413490671L;
    private String id;
    private String userNo;
    private String email;
    private String identityType;
    private String identifier;
    private String credential;
    private String creator;
    private byte activeStatus;
}
