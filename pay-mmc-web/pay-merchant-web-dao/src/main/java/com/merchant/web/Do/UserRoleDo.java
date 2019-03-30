package com.merchant.web.Do;

import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDo implements Serializable {
    private String  userNo;
    private Integer roleId;
    private String  merchantId;
    private Integer type;
}
