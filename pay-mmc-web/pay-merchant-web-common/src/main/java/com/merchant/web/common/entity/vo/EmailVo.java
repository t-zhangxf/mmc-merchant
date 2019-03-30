package com.merchant.web.common.entity.vo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String token;
    private String email;
    private String password;
}
