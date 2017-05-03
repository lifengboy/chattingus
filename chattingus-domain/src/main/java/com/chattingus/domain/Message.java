package com.chattingus.domain;

import java.util.*;
import java.io.Serializable;

/**
 * @author
 * @date 2017-04-24
 */
public class Message implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer mId;
    /**
     * 发起用户主键id
     */
    private Integer fromUserId;
    /**
     * 目的主键id
     */
    private Integer toUserId;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 是否已读
     */
    private Integer readOrNot;
    /**
     * 消息类型 0是验证消息，1是好友之间信息，2是群聊
     */
    private Integer messageType;
    /**
     * 发送日期
     */
    private Date sendTime;


    /**
     * @return mId id
     */
    public Integer getMId() {
        return mId;
    }

    /**
     * @param mId id
     */
    public void setMId(Integer mId) {
        this.mId = mId;
    }

    /**
     * @return fromUserId 发起用户主键id
     */
    public Integer getFromUserId() {
        return fromUserId;
    }

    /**
     * @param fromUserId 发起用户主键id
     */
    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    /**
     * @return toUserId 目的主键id
     */
    public Integer getToUserId() {
        return toUserId;
    }

    /**
     * @param toUserId 目的主键id
     */
    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * @return content 消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content 消息内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return readOrNot 是否已读
     */
    public Integer getReadOrNot() {
        return readOrNot;
    }

    /**
     * @param readOrNot 是否已读
     */
    public void setReadOrNot(Integer readOrNot) {
        this.readOrNot = readOrNot;
    }

    /**
     * @return messageType 消息类型
     */
    public Integer getMessageType() {
        return messageType;
    }

    /**
     * @param messageType 消息类型
     */
    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    /**
     * @return sendTime 发送日期
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime 发送日期
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

}