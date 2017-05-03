package com.chattingus.query;

import java.util.*;

/**
 * @author
 */
public class UserQuery extends BaseQuery {

    /**==============================批量查询、更新、删除时的Where条件设置==================================**/
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
     * 用户名 *
     */
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 密码 *
     */
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 用户昵称 *
     */
    private String nick;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * 用户头像 *
     */
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 真实姓名 *
     */
    private String realName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 生日 *
     */
    private String birthday;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 性别 *
     */
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 手机号码 *
     */
    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 注册日期 *
     */
    private Date registerDateStart;

    public Date getRegisterDateStart() {
        return registerDateStart;
    }

    public void setRegisterDateStart(Date registerDate) {
        this.registerDateStart = registerDate;
    }

    private Date registerDateEnd;

    public Date getRegisterDateEnd() {
        return registerDateEnd;
    }

    public void setRegisterDateEnd(Date registerDate) {
        this.registerDateEnd = registerDate;
    }

    private Date registerDateEqual;

    public Date getRegisterDateEqual() {
        return registerDateEqual;
    }

    public void setRegisterDateEqual(Date registerDate) {
        this.registerDateEqual = registerDate;
    }

    /**
     * 电子邮件 *
     */
    private String eMail;

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * 权限，分为user和manager，默认user *
     */
    private String privilege;

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    /**
     * 状态，0是离线状态，1表示在线 *
     */
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 用户登陆后所处的ip *
     */
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
     * 设置排序按属性：用户主键id
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyUserId(boolean isAsc) {
        orderFields.add(new OrderField("user_id", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：用户名
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyUsername(boolean isAsc) {
        orderFields.add(new OrderField("username", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：密码
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyPassword(boolean isAsc) {
        orderFields.add(new OrderField("password", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：用户昵称
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyNick(boolean isAsc) {
        orderFields.add(new OrderField("nick", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：用户头像
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyAvatar(boolean isAsc) {
        orderFields.add(new OrderField("avatar", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：真实姓名
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyRealName(boolean isAsc) {
        orderFields.add(new OrderField("real_name", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：生日
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyBirthday(boolean isAsc) {
        orderFields.add(new OrderField("birthday", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：性别
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyGender(boolean isAsc) {
        orderFields.add(new OrderField("gender", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：手机号码
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyTel(boolean isAsc) {
        orderFields.add(new OrderField("tel", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：注册日期
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyRegisterDate(boolean isAsc) {
        orderFields.add(new OrderField("register_date", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：电子邮件
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyEMail(boolean isAsc) {
        orderFields.add(new OrderField("e_mail", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：权限，分为user和manager，默认user
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyPrivilege(boolean isAsc) {
        orderFields.add(new OrderField("privilege", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：状态，0是离线状态，1表示在线
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyStatus(boolean isAsc) {
        orderFields.add(new OrderField("status", isAsc ? "ASC" : "DESC"));
    }

    /**
     * 设置排序按属性：用户登陆后所处的ip
     *
     * @param isAsc 是否升序，否则为降序
     */
    public void orderbyIp(boolean isAsc) {
        orderFields.add(new OrderField("ip", isAsc ? "ASC" : "DESC"));
    }

    private String fields;
    /**
     * 提供自定义字段使用
     */
    private static Map<String, String> fieldMap;

    private static Map<String, String> getFieldSet() {
        if (fieldMap == null) {
            fieldMap = new HashMap<String, String>();
            fieldMap.put("user_id", "userId");
            fieldMap.put("username", "username");
            fieldMap.put("password", "password");
            fieldMap.put("nick", "nick");
            fieldMap.put("avatar", "avatar");
            fieldMap.put("real_name", "realName");
            fieldMap.put("birthday", "birthday");
            fieldMap.put("gender", "gender");
            fieldMap.put("tel", "tel");
            fieldMap.put("register_date", "registerDate");
            fieldMap.put("e_mail", "eMail");
            fieldMap.put("privilege", "privilege");
            fieldMap.put("status", "status");
            fieldMap.put("ip", "ip");
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
