package com.merchant.web.common.entity.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailRes implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bizCode;
    private Object data;
    private String message;
}
