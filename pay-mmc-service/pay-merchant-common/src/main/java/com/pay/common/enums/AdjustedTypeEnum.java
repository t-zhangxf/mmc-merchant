package com.pay.common.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * 权限设置操作类型
 */
@Getter
@ToString
public enum AdjustedTypeEnum {
	upgrade(0, "upgrade to Admin", "升级为管理员"),
	downgrade(1,"adjust to Operator", "调整为普通操作员"),
	;
	private final Integer type;

	private final String EnglishDesc;

	private final String ChineseDesc;

	AdjustedTypeEnum(Integer type, String englishDesc, String chineseDesc) {
		this.type = type;
		EnglishDesc = englishDesc;
		ChineseDesc = chineseDesc;
	}

	public Integer getType() {
		return type;
	}

	public String getEnglishDesc() {
		return EnglishDesc;
	}

	public String getChineseDesc() {
		return ChineseDesc;
	}

	/**
	 * Get AdjustedTypeEnum By type
	 *
	 * @param type
	 * @return
	 */
	public final static AdjustedTypeEnum getByType(Integer type) {
		for (AdjustedTypeEnum t : AdjustedTypeEnum.values()) {
			if (t.getType() == type) {
				return t;
			}
		}
		return null;
	}
}
