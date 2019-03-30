package com.merchant.web.common.entity.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailReq implements Serializable {
    private   String token;
}
