package com.pay.merchant.req;

import lombok.*;

/**
 * 
 * @author liang_huiLing  Get Member Detail Request
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetMemberDetailRequest {
	/**
	 * 商户号
	 */
	private String merchant_no;
}
