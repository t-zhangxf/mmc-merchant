package com.merchant.web.Do;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDo implements Serializable {
    private static final long serialVersionUID = 3026591685413490671L;
    private  Integer id;
    private  String  email;
    private  String  mobile;
    private  String  userNo;
    private  String  userStatus;
    private  String  identifier;
}
