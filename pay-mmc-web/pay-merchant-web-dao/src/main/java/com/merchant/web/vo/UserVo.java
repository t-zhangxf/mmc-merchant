package com.merchant.web.vo;

import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {
    private static final long serialVersionUID = 3026591685413490671L;
    private String userNo;
    private String identityType;
    private String identifier;
    private Object credential;
    private String userStatus;
}
