package com.chattingus.query;

import java.util.*;

/**
 * @author
 */
public class MessageQuery extends BaseQuery {

    /**==============================批量查询、更新、删除时的Where条件设置==================================**/
    /**
     * id *
     */
    private Integer mId;

    public Integer getMId() {
        return mId;
    }

    public void setMId(Integer mId) {
        this.mId = mId;
    }

    /**
     * 发起用户主键id *
     */
    private Integer fromUserId;

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    /**
     * 目的主键id *
     */
    private Integer toUserId;

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * 消息内容 *
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 是否已读 *
     */
    private Integer readOrNot;

    public Integer getReadOrNot() {
        return readOrNot;
    }

    public void setReadOrNot(Integer readOrNot) {
        this.readOrNot = readOrNot;
    }

    /**
     * 消息类型 *
     */
    private Integer messageType;

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    /**
     * 发送日期 *
     */
    private Date sendTimeStart;

    public Date getSendTimeStart() {
        return sendTimeStart;
    }

    public void setSendTimeStart(Date sendTime) {
        this.sendTimeStart = sendTime;
    }

    private Date sendTimeEnd;

    public Date getSendTimeEnd() {
        return sendTimeEnd;
    }

    public void setSendTimeEnd(Date sendTime) {
        this.sendTimeEnd = sendTime;
    }

    private Date sendTimeEqual;

    public Date getSendTimeEqual() {
        return sendTimeEqual;
    }

    public void setSendTimeEqual(Date sendTime) {
        this.sendTimeEqual = sendTime;
    }

    /**
     * ==============================批量查询时的Order条件顺序设置==================================*
     */
    public class OrderField {
        public OrderField(String fieldName, String order) {
            super();
            this.fieldName = fieldName;
            this.order = order;
        }

        private String fieldName;
        private String order;

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }

    /**==============================批量查询时的Order条件顺序设置==================================**/
    /**
     * 排序列表字段*
     */
    private List<OrderField> orderFields = new ArrayList<OrderField>();

    /**
     * 设置排序按属性：id
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyMId(boolean isAsc) {
        orderFields.add(new OrderField("m_id", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：发起用户主键id
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyFromUserId(boolean isAsc) {
        orderFields.add(new OrderField("from_user_id", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：目的主键id
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyToUserId(boolean isAsc) {
        orderFields.add(new OrderField("to_user_id", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：消息内容
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyContent(boolean isAsc) {
        orderFields.add(new OrderField("content", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：是否已读
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyReadOrNot(boolean isAsc) {
        orderFields.add(new OrderField("read_or_not", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：消息类型
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyMessageType(boolean isAsc) {
        orderFields.add(new OrderField("message_type", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：发送日期
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbySendTime(boolean isAsc) {
        orderFields.add(new OrderField("send_time", isAsc ? "ASC" : "DESC"));
    }

    private String fields;
    /**
     * 提供自定义字段使用
     */
    private static Map<String, String> fieldMap;

    private static Map<String, String> getFieldSet() {
        if (fieldMap == null) {
            fieldMap = new HashMap<String, String>();
            fieldMap.put("m_id", "mId");
            fieldMap.put("from_user_id", "fromUserId");
            fieldMap.put("to_user_id", "toUserId");
            fieldMap.put("content", "content");
            fieldMap.put("read_or_not", "readOrNot");
            fieldMap.put("message_type", "messageType");
            fieldMap.put("send_time", "sendTime");
        }
        return fieldMap;
    }

    public String getFields() {
        return this.fields;
    }

    public void setFields(String fields) {
        String[] array = fields.split(",");
        StringBuffer buffer = new StringBuffer();
        for (String field : array) {
            if (getFieldSet().containsKey(field)) {
                buffer.append(field).append(" as ").append(getFieldSet().get(field)).append(" ,");
            }
            if (getFieldSet().containsKey("`" + field + "`")) {
                buffer.append("`" + field + "`").append(" as ").append(getFieldSet().get(field)).append(" ,");
            }
        }
        if (buffer.length() != 0) {
            this.fields = buffer.substring(0, buffer.length() - 1);
        } else {
            this.fields = " 1 ";//没有一个参数可能会报错
        }
    }
}
