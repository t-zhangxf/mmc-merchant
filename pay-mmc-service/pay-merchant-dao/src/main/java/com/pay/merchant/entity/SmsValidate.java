package com.pay.merchant.entity;

import java.util.Date;

public class SmsValidate {
    private Integer id;

    private String userNo;

    private Byte senderType;

    private String sender;

    private String validateCode;

    private String sms;

    private Date deadLine;

    private Byte usable;

    private Byte sended;

    private Date createTime;

    private String creator;

    private Date modifiedTime;

    private String modifier;

    private Byte isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Byte getSenderType() {
        return senderType;
    }

    public void setSenderType(Byte senderType) {
        this.senderType = senderType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public Byte getUsable() {
        return usable;
    }

    public void setUsable(Byte usable) {
        this.usable = usable;
    }

    public Byte getSended() {
        return sended;
    }

    public void setSended(Byte sended) {
        this.sended = sended;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}