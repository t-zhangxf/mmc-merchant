package com.pay.merchant.req;

import lombok.*;

/**
 * 
 * @author liang_huiLing  Modify Member Detail Request
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModifyMemberDetailRequest {
	/**
	 * 银行卡号
	 */
	private String bank_card_no;
	/**
	 * 银行名称
	 */
	private String bank_name;
	/**
	 * 分支行名称
	 */
	private String branch_no;
	/**
	 * 企业地址
	 */
	private String company_address;
	/**
	 * 企业名称
	 */
	private String company_name;
	/**
	 * 官网地址
	 */
	private String company_website_address;
	/**
	 * 商户对接邮箱
	 */
	private String contact_mail;
	/**
	 * 商务对接人
	 */
	private String contact_person;
	/**
	 * 商务对接电话
	 */
	private String contact_phone;
	/**
	 * 联系人邮箱
	 */
	private String corporation_mail;
	/**
	 * 联系人姓名
	 */
	private String corporation_name;
	/**
	 * 联系人电话
	 */
	private String corporation_phone;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 显示名称
	 */
	private String display_name;
	/**
	 * 税务GSTIN号
	 */
	private String gstin;
	/**
	 * 税务gstin号截止日
	 */
	private String gstin_expiration_date;
	/**
	 * 税务gstin影印件url
	 */
	private String gstin_photo_url;
	/**
	 * IFSC号
	 */
	private String ifsc;
	/**
	 * 营业执照截止日
	 */
	private String licence_expiration_date;
	/**
	 * 营业执照编号
	 */
	private String licence_no;
	/**
	 * 营业执照影印件url
	 */
	private String licence_photo_url;
	/**
	 * 行业分类
	 */
	private String mcc_code;
	/**
	 * 商户名称
	 */
	private String merchant_name;
	/**
	 * 商户号
	 */
	private String merchant_no;
	/**
	 * 商户类型
	 */
	private String merchant_type;
	/**
	 * 税务PAN号
	 */
	private String pan;
	/**
	 * 税务pan号截止日
	 */
	private String pan_expiration_date;
	/**
	 * 税务pan影印件url
	 */
	private String pan_photo_url;
	/**
	 * 备用联系人邮箱
	 */
	private String standby_mail;
	/**
	 * 备用联系人姓名
	 */
	private String standby_name;
	/**
	 * 备用联系人电话
	 */
	private String standby_phone;
}
