package com.chattingus.services;

import com.chattingus.dao.UserDAO;
import com.chattingus.query.UserQuery;
import com.chattingus.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 
 * @since 2017-04-07
 */
@Service
public class UserService {

    @Resource
    UserDAO userDAO;

    /**
     * 插入
     */
    public Integer addUser(User user) {
        return userDAO.addUser(user);
    }


    /**
     * 根据userId查找
     */
    public User getUserByUserId(Integer userId) {
        return userDAO.getUserByUserId(userId);
    }

    /**
     * 根据主键查找
    */
    public User getUserByKey(Integer userId, String username) {
        return userDAO.getUserByKey(userId, username);
    }

    /**
     * 根据主键查找
    */
    public User getUserByKey(User user, Integer userId, String username) {
        return userDAO.getUserByKey(userId, username);
    }

        
    /**
     * 根据主键组进行查找
    */
    public List<User> getUserByKeys(User user, Integer userId,List<String> usernameList) {
        return userDAO.getUserByKeys(userId, usernameList);
    }

    /**
     * 根据主键组进行查找
    */
    public List<User> getUserByKeys(Integer userId,List<String> usernameList) {
        return userDAO.getUserByKeys(userId,usernameList);
    }
        
    /**
     * 根据主键删除
     */
    public Integer deleteByKey(User user, Integer userId, String username) {
        return userDAO.deleteByKey(userId, username);
    }

    /**
     * 根据主键组进行删除
     */
    public Integer deleteByKeys(User user, Integer userId,List<String> usernameList) {
        return userDAO.deleteByKeys(userId,usernameList);
    }
    /**
     * 根据主键更新
     */
    public Integer updateUserByKey(User user) {
        return userDAO.updateUserByKey(user);
    }
    
    /**
     * 根据条件查询分页查询
     */
//    public Result<User> getUserListWithPage(User user, UserQuery userQuery) {
//        return userDAO.getUserListWithPage(userQuery);
//    }
    /**
     * 根据条件查询
     */
    public List<User> getUserList(UserQuery userQuery) {
        return userDAO.getUserList(userQuery);
    }


    public User getUserByUserNamePassWord(User user){
        return userDAO.getUserByUserNamePassWord(user);
    }

    public User saveUserMessage(String remoteAddr, User queryUser) {
        queryUser.setIp(remoteAddr);
        queryUser.setStatus("1");
        userDAO.updateUserByUsername(queryUser);
        return queryUser;
    }
}
