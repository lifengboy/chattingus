package com.chattingus.domain;

import java.util.*;
import java.io.Serializable;

/**
 * @author
 * @date 2017-04-19
 */
public class User implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户主键id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nick;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 性别
     */
    private String gender;
    /**
     * 手机号码
     */
    private String tel;
    /**
     * 注册日期
     */
    private Date registerDate;
    /**
     * 电子邮件
     */
    private String eMail;
    /**
     * 权限，分为user和manager，默认user
     */
    private String privilege;
    /**
     * 状态，0是离线状态，1表示在线
     */
    private String status;
    /**
     * 用户登陆后所处的ip
     */
    private String ip;


    /**
     * @return userId 用户主键id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId 用户主键id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return username 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return nick 用户昵称
     */
    public String getNick() {
        return nick;
    }

    /**
     * @param nick 用户昵称
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * @return avatar 用户头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar 用户头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return realName 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return birthday 生日
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday 生日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return gender 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender 性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return tel 手机号码
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel 手机号码
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return registerDate 注册日期
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * @param registerDate 注册日期
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * @return eMail 电子邮件
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * @param eMail 电子邮件
     */
    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * @return privilege 权限，分为user和manager，默认user
     */
    public String getPrivilege() {
        return privilege;
    }

    /**
     * @param privilege 权限，分为user和manager，默认user
     */
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    /**
     * @return status 状态，0是离线状态，1表示在线
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 状态，0是离线状态，1表示在线
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return ip 用户登陆后所处的ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip 用户登陆后所处的ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

}