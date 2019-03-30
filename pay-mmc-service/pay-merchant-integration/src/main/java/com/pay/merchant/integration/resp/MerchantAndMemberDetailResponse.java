package com.pay.merchant.integration.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantAndMemberDetailResponse<T> {
	/**
	 * 返回码
	 */
	private String code;
	/**
	 * 返回描述
	 */
	private String msg;

	private data data;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class data {
		/**
		 * 会员信息
		 */
		private member member;

		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		@Builder
		public static class member {
			/**
			 * 公司地址
			 */
			private String companyAddr;
			/**
			 * 国家
			 */
			private String country;
			/**
			 * 会员ID
			 */
			private String memberId;
			/**
			 * 会员名称
			 */
			private String memberName;
			/**
			 * 创建时间
			 */
			private Date openTime;

			/**
			 * 开户请求流水号
			 */
			private String requestNo;

			/**
			 * 最新更新时间
			 */
			private Date updateTime;

			/**
			 * 银行卡列表
			 */
			private List<bankAccounts> bankAccounts;

			@Getter
			@Setter
			@NoArgsConstructor
			@AllArgsConstructor
			@Builder
			public static class bankAccounts {
				/**
				 * 银行账户
				 */
				private String bankAccount;

				/**
				 * 银行名称
				 */
				private String bankName;

				/**
				 * 支行名称
				 */
				private String branchBank;

				/**
				 * IFSC账号
				 */
				private String ifscAccount;
			}
			/**
			 * 证件信息
			 */
			private List<certficates> certficates;

			@Getter
			@Setter
			@NoArgsConstructor
			@AllArgsConstructor
			@Builder
			public static class certficates {
				/**
				 * 证件号码
				 */
				private String certificateNo;

				/**
				 * 证件类型 税务PAN号：PAN 税务GSTIN号:GSTIN 组织机构代码证:ORG_NO 工商营业执照编号：LICENSE 税务登记证：TAX_NO，必填
				 */
				private String certificateType;

				/**
				 * 影印件地址
				 */
				private String photoUrl;

				/**
				 * 证件有效期
				 */
				private String validPeriod;
			}
			/**
			 * 联系人信息
			 */
			private List<contacts> contacts;

			@Getter
			@Setter
			@NoArgsConstructor
			@AllArgsConstructor
			@Builder
			public static class contacts {
				/**
				 * 联系人电子邮件
				 */
				private String contactsEmail;

				/**
				 * 联系人姓名
				 */
				private String contactsName;

				/**
				 * 联系人电话
				 */
				private String contactsPhone;

			}

		}
		/**
		 * 商户信息
		 */
		private merchant merchant;

		@Getter
		@Setter
		@NoArgsConstructor
		@AllArgsConstructor
		@Builder
		public static class merchant{
			/**
			 * BD电子邮件
			 */
			private String bdEmail;
			/**
			 * BD联系电话
			 */
			private String bdMobile;
			/**
			 * BD姓名
			 */
			private String bdName;
			/**
			 * 商户行业
			 */
			private String industry;
			/**
			 * 商户logo
			 */
			private String logoUrl;
			/**
			 * 会员ID
			 */
			private String memberId;
			/**
			 * 商户App
			 */
			private String merchantApp;
			/**
			 * 商户名称
			 */
			private String merchantName;
			/**
			 * 商户编号
			 */
			private String merchantNo;
			/**
			 * 商户状态
			 */
			private String merchantStatus;
			/**
			 * 1：特约商户 2：普通商户
			 */
			private String merchantType;
			/**
			 * 商户网址
			 */
			private String merchantUrl;
			/**
			 * 商户简称
			 */
			private String simpleName;
		}

	}


}
