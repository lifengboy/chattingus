package com.chattingus.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 */
public class FriendQuery extends BaseQuery {

    /**==============================批量查询、更新、删除时的Where条件设置==================================**/
    /**
     * id *
     */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 用户主键id *
     */
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 好友主键id *
     */
    private Integer friendId;

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
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
    public void orderbyId(boolean isAsc) {
        orderFields.add(new OrderField("id", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：用户主键id
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyUserId(boolean isAsc) {
        orderFields.add(new OrderField("user_id", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：好友主键id
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyFriendId(boolean isAsc) {
        orderFields.add(new OrderField("friend_id", isAsc ? "ASC" : "DESC"));
    }

    private String fields;
    /**
     * 提供自定义字段使用
     */
    private static Map<String, String> fieldMap;

    private static Map<String, String> getFieldSet() {
        if (fieldMap == null) {
            fieldMap = new HashMap<String, String>();
            fieldMap.put("id", "id");
            fieldMap.put("user_id", "userId");
            fieldMap.put("friend_id", "friendId");
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
