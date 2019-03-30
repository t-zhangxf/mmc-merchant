package com.pay.merchant.req;

import lombok.*;

import java.math.BigDecimal;

/**
 * 
 * @author liang_huiLing  Merchant Add Request
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantAddRequest {
	/**
	 * 显示名称
	 */
	private String display_name;
	/**
	 * 行业分类（0：数娱-游戏，1：电子商务，2：数娱-视频，3：互联网金融，4：其他）
	 */
	private Integer mcc_code;
	/**
	 * 商户名称
	 */
	private String merchant_name;
	/**
	 * 商户类型（0：企业，1：个人）
	 */
	private String merchant_type;
}
