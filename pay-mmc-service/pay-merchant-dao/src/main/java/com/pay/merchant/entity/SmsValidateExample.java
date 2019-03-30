package com.pay.merchant.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmsValidateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SmsValidateExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserNoIsNull() {
            addCriterion("user_no is null");
            return (Criteria) this;
        }

        public Criteria andUserNoIsNotNull() {
            addCriterion("user_no is not null");
            return (Criteria) this;
        }

        public Criteria andUserNoEqualTo(String value) {
            addCriterion("user_no =", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotEqualTo(String value) {
            addCriterion("user_no <>", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoGreaterThan(String value) {
            addCriterion("user_no >", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoGreaterThanOrEqualTo(String value) {
            addCriterion("user_no >=", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLessThan(String value) {
            addCriterion("user_no <", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLessThanOrEqualTo(String value) {
            addCriterion("user_no <=", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLike(String value) {
            addCriterion("user_no like", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotLike(String value) {
            addCriterion("user_no not like", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoIn(List<String> values) {
            addCriterion("user_no in", values, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotIn(List<String> values) {
            addCriterion("user_no not in", values, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoBetween(String value1, String value2) {
            addCriterion("user_no between", value1, value2, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotBetween(String value1, String value2) {
            addCriterion("user_no not between", value1, value2, "userNo");
            return (Criteria) this;
        }

        public Criteria andSenderTypeIsNull() {
            addCriterion("sender_type is null");
            return (Criteria) this;
        }

        public Criteria andSenderTypeIsNotNull() {
            addCriterion("sender_type is not null");
            return (Criteria) this;
        }

        public Criteria andSenderTypeEqualTo(Byte value) {
            addCriterion("sender_type =", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeNotEqualTo(Byte value) {
            addCriterion("sender_type <>", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeGreaterThan(Byte value) {
            addCriterion("sender_type >", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("sender_type >=", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeLessThan(Byte value) {
            addCriterion("sender_type <", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeLessThanOrEqualTo(Byte value) {
            addCriterion("sender_type <=", value, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeIn(List<Byte> values) {
            addCriterion("sender_type in", values, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeNotIn(List<Byte> values) {
            addCriterion("sender_type not in", values, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeBetween(Byte value1, Byte value2) {
            addCriterion("sender_type between", value1, value2, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("sender_type not between", value1, value2, "senderType");
            return (Criteria) this;
        }

        public Criteria andSenderIsNull() {
            addCriterion("sender is null");
            return (Criteria) this;
        }

        public Criteria andSenderIsNotNull() {
            addCriterion("sender is not null");
            return (Criteria) this;
        }

        public Criteria andSenderEqualTo(String value) {
            addCriterion("sender =", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotEqualTo(String value) {
            addCriterion("sender <>", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderGreaterThan(String value) {
            addCriterion("sender >", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderGreaterThanOrEqualTo(String value) {
            addCriterion("sender >=", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderLessThan(String value) {
            addCriterion("sender <", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderLessThanOrEqualTo(String value) {
            addCriterion("sender <=", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderLike(String value) {
            addCriterion("sender like", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotLike(String value) {
            addCriterion("sender not like", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderIn(List<String> values) {
            addCriterion("sender in", values, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotIn(List<String> values) {
            addCriterion("sender not in", values, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderBetween(String value1, String value2) {
            addCriterion("sender between", value1, value2, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotBetween(String value1, String value2) {
            addCriterion("sender not between", value1, value2, "sender");
            return (Criteria) this;
        }

        public Criteria andValidateCodeIsNull() {
            addCriterion("validate_code is null");
            return (Criteria) this;
        }

        public Criteria andValidateCodeIsNotNull() {
            addCriterion("validate_code is not null");
            return (Criteria) this;
        }

        public Criteria andValidateCodeEqualTo(String value) {
            addCriterion("validate_code =", value, "validateCode");
            return (Criteria) this;
        }

        public Criteria andValidateCodeNotEqualTo(String value) {
            addCriterion("validate_code <>", value, "validateCode");
            return (Criteria) this;
        }

        public Criteria andValidateCodeGreaterThan(String value) {
            addCriterion("validate_code >", value, "validateCode");
            return (Criteria) this;
        }

        public Criteria andValidateCodeGreaterThanOrEqualTo(String value) {
            addCriterion("validate_code >=", value, "validateCode");
            return (Criteria) this;
        }

        public Criteria andValidateCodeLessThan(String value) {
            addCriterion("validate_code <", value, "validateCode");
            return (Criteria) this;
        }

        public Criteria andValidateCodeLessThanOrEqualTo(String value) {
            addCriterion("validate_code <=", value, "validateCode");
            return (Criteria) this;
        }

        public Criteria andValidateCodeLike(String value) {
            addCriterion("validate_code like", value, "validateCode");
            return (Criteria) this;
        }

        public Criteria andValidateCodeNotLike(String value) {
            addCriterion("validate_code not like", value, "validateCode");
            return (Criteria) this;
        }

        public Criteria andValidateCodeIn(List<String> values) {
            addCriterion("validate_code in", values, "validateCode");
            return (Criteria) this;
        }

        public Criteria andValidateCodeNotIn(List<String> values) {
            addCriterion("validate_code not in", values, "validateCode");
            return (Criteria) this;
        }

        public Criteria andValidateCodeBetween(String value1, String value2) {
            addCriterion("validate_code between", value1, value2, "validateCode");
            return (Criteria) this;
        }

        public Criteria andValidateCodeNotBetween(String value1, String value2) {
            addCriterion("validate_code not between", value1, value2, "validateCode");
            return (Criteria) this;
        }

        public Criteria andSmsIsNull() {
            addCriterion("sms is null");
            return (Criteria) this;
        }

        public Criteria andSmsIsNotNull() {
            addCriterion("sms is not null");
            return (Criteria) this;
        }

        public Criteria andSmsEqualTo(String value) {
            addCriterion("sms =", value, "sms");
            return (Criteria) this;
        }

        public Criteria andSmsNotEqualTo(String value) {
            addCriterion("sms <>", value, "sms");
            return (Criteria) this;
        }

        public Criteria andSmsGreaterThan(String value) {
            addCriterion("sms >", value, "sms");
            return (Criteria) this;
        }

        public Criteria andSmsGreaterThanOrEqualTo(String value) {
            addCriterion("sms >=", value, "sms");
            return (Criteria) this;
        }

        public Criteria andSmsLessThan(String value) {
            addCriterion("sms <", value, "sms");
            return (Criteria) this;
        }

        public Criteria andSmsLessThanOrEqualTo(String value) {
            addCriterion("sms <=", value, "sms");
            return (Criteria) this;
        }

        public Criteria andSmsLike(String value) {
            addCriterion("sms like", value, "sms");
            return (Criteria) this;
        }

        public Criteria andSmsNotLike(String value) {
            addCriterion("sms not like", value, "sms");
            return (Criteria) this;
        }

        public Criteria andSmsIn(List<String> values) {
            addCriterion("sms in", values, "sms");
            return (Criteria) this;
        }

        public Criteria andSmsNotIn(List<String> values) {
            addCriterion("sms not in", values, "sms");
            return (Criteria) this;
        }

        public Criteria andSmsBetween(String value1, String value2) {
            addCriterion("sms between", value1, value2, "sms");
            return (Criteria) this;
        }

        public Criteria andSmsNotBetween(String value1, String value2) {
            addCriterion("sms not between", value1, value2, "sms");
            return (Criteria) this;
        }

        public Criteria andDeadLineIsNull() {
            addCriterion("dead_line is null");
            return (Criteria) this;
        }

        public Criteria andDeadLineIsNotNull() {
            addCriterion("dead_line is not null");
            return (Criteria) this;
        }

        public Criteria andDeadLineEqualTo(Date value) {
            addCriterion("dead_line =", value, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineNotEqualTo(Date value) {
            addCriterion("dead_line <>", value, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineGreaterThan(Date value) {
            addCriterion("dead_line >", value, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineGreaterThanOrEqualTo(Date value) {
            addCriterion("dead_line >=", value, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineLessThan(Date value) {
            addCriterion("dead_line <", value, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineLessThanOrEqualTo(Date value) {
            addCriterion("dead_line <=", value, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineIn(List<Date> values) {
            addCriterion("dead_line in", values, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineNotIn(List<Date> values) {
            addCriterion("dead_line not in", values, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineBetween(Date value1, Date value2) {
            addCriterion("dead_line between", value1, value2, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineNotBetween(Date value1, Date value2) {
            addCriterion("dead_line not between", value1, value2, "deadLine");
            return (Criteria) this;
        }

        public Criteria andUsableIsNull() {
            addCriterion("usable is null");
            return (Criteria) this;
        }

        public Criteria andUsableIsNotNull() {
            addCriterion("usable is not null");
            return (Criteria) this;
        }

        public Criteria andUsableEqualTo(Byte value) {
            addCriterion("usable =", value, "usable");
            return (Criteria) this;
        }

        public Criteria andUsableNotEqualTo(Byte value) {
            addCriterion("usable <>", value, "usable");
            return (Criteria) this;
        }

        public Criteria andUsableGreaterThan(Byte value) {
            addCriterion("usable >", value, "usable");
            return (Criteria) this;
        }

        public Criteria andUsableGreaterThanOrEqualTo(Byte value) {
            addCriterion("usable >=", value, "usable");
            return (Criteria) this;
        }

        public Criteria andUsableLessThan(Byte value) {
            addCriterion("usable <", value, "usable");
            return (Criteria) this;
        }

        public Criteria andUsableLessThanOrEqualTo(Byte value) {
            addCriterion("usable <=", value, "usable");
            return (Criteria) this;
        }

        public Criteria andUsableIn(List<Byte> values) {
            addCriterion("usable in", values, "usable");
            return (Criteria) this;
        }

        public Criteria andUsableNotIn(List<Byte> values) {
            addCriterion("usable not in", values, "usable");
            return (Criteria) this;
        }

        public Criteria andUsableBetween(Byte value1, Byte value2) {
            addCriterion("usable between", value1, value2, "usable");
            return (Criteria) this;
        }

        public Criteria andUsableNotBetween(Byte value1, Byte value2) {
            addCriterion("usable not between", value1, value2, "usable");
            return (Criteria) this;
        }

        public Criteria andSendedIsNull() {
            addCriterion("sended is null");
            return (Criteria) this;
        }

        public Criteria andSendedIsNotNull() {
            addCriterion("sended is not null");
            return (Criteria) this;
        }

        public Criteria andSendedEqualTo(Byte value) {
            addCriterion("sended =", value, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedNotEqualTo(Byte value) {
            addCriterion("sended <>", value, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedGreaterThan(Byte value) {
            addCriterion("sended >", value, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedGreaterThanOrEqualTo(Byte value) {
            addCriterion("sended >=", value, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedLessThan(Byte value) {
            addCriterion("sended <", value, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedLessThanOrEqualTo(Byte value) {
            addCriterion("sended <=", value, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedIn(List<Byte> values) {
            addCriterion("sended in", values, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedNotIn(List<Byte> values) {
            addCriterion("sended not in", values, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedBetween(Byte value1, Byte value2) {
            addCriterion("sended between", value1, value2, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedNotBetween(Byte value1, Byte value2) {
            addCriterion("sended not between", value1, value2, "sended");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIsNull() {
            addCriterion("modified_time is null");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIsNotNull() {
            addCriterion("modified_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeEqualTo(Date value) {
            addCriterion("modified_time =", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotEqualTo(Date value) {
            addCriterion("modified_time <>", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeGreaterThan(Date value) {
            addCriterion("modified_time >", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modified_time >=", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLessThan(Date value) {
            addCriterion("modified_time <", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLessThanOrEqualTo(Date value) {
            addCriterion("modified_time <=", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIn(List<Date> values) {
            addCriterion("modified_time in", values, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotIn(List<Date> values) {
            addCriterion("modified_time not in", values, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeBetween(Date value1, Date value2) {
            addCriterion("modified_time between", value1, value2, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotBetween(Date value1, Date value2) {
            addCriterion("modified_time not between", value1, value2, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("modifier like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("modifier not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Byte value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Byte value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Byte value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Byte value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Byte value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Byte> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Byte> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Byte value1, Byte value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Byte value1, Byte value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}