package com.merchant.web.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 3026591685413490671L;
    private String userNo;
    private String identityType;
    private String identifier;
    private String credential;
    private String userStatus;
    private String email;
}
