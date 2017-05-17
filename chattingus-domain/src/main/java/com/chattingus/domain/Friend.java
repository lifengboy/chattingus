package com.chattingus.domain;

import java.io.Serializable;

/**
 * @author
 * @date 2017-04-19
 */
public class Friend implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    /**
     * id
     */
    private Integer id;
    /**
     * 用户主键id
     */
    private Integer userId;
    /**
     * 好友主键id
     */
    private Integer friendId;


    /**
     * @return id id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
     * @return friendId 好友主键id
     */
    public Integer getFriendId() {
        return friendId;
    }

    /**
     * @param friendId 好友主键id
     */
    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

}