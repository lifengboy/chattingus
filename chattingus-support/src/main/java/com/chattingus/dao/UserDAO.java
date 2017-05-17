package com.chattingus.dao;

import com.chattingus.domain.User;
import com.chattingus.query.UserQuery;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @date 2017-04-18
 */

@Repository
public class UserDAO extends CobarClientBaseDao {


    /**
     * 添加
     */
    public Integer addUser(User user) {
        return (Integer) getSqlMapClientTemplate().insert("User.insertUser", user);
    }

    /**
     * 根据主键查找
     */
    public User getUserByKey(Integer userId, String username) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("userId", userId);
        params.put("username", username);
        User result = (User) getSqlMapClientTemplate().queryForObject(
                "User.getUserByKey", params);
        return result;
    }
    /**
     * 根据userId查找
     */
    public User getUserByUserId(Integer userId) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("userId", userId);
        User result = (User) getSqlMapClientTemplate().queryForObject(
                "User.getUserByUserId", params);
        return result;
    }

    /**
     * 根据主键批量查找
     */
    public List<User> getUserByKeys(Integer userId, List<String> usernameList) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("userId", userId);
        params.put("keys", usernameList);
        return (List<User>) getSqlMapClientTemplate().queryForList("User.getUsersByKeys", params);
    }

    /**
     * 根据主键删除
     */
    public Integer deleteByKey(Integer userId, String username) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("userId", userId);
        params.put("username", username);
        Integer row = (Integer) getSqlMapClientTemplate().delete("User.deleteByKey", params);
        return row;
    }

    /**
     * 根据主键批量删除
     */
    public Integer deleteByKeys(Integer userId, List<String> usernameList) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("userId", userId);
        params.put("keys", usernameList);
        Integer row = (Integer) getSqlMapClientTemplate().delete("User.deleteByKeys", params);
        return row;
    }

    /**
     * 根据username更新
     */
    public Integer updateUserByUsername(User user) {
        return (Integer) getSqlMapClientTemplate().update("User.updateUserByUsername", user);
    }

    /**
     * 根据主键更新
     */
    public Integer updateUserByKey(User user) {
        return (Integer) getSqlMapClientTemplate().update("User.updateUserByKey", user);
    }

    /**
     * 根据条件查询分页查询
     */
    @SuppressWarnings("unchecked")
    public Result<User> getUserListWithPage(UserQuery userQuery) {
        Result<User> rs = new Result<User>();
        rs.setCount((Integer) getSqlMapClientTemplate().queryForObject("User.getUserListCount", userQuery));
        if (userQuery.getFields() != null && userQuery.getFields() != "") {
            rs.setList((List<User>) getSqlMapClientTemplate().queryForList("User.getUserListWithPageFields", userQuery));
        } else {
            rs.setList((List<User>) getSqlMapClientTemplate().queryForList("User.getUserListWithPage", userQuery));
        }
        return rs;
    }

    /**
     * 根据条件查询
     */
    @SuppressWarnings("unchecked")
    public List<User> getUserList(UserQuery userQuery) {
        if (userQuery.getFields() != null && userQuery.getFields() != "") {
            return (List<User>) getSqlMapClientTemplate().queryForList("User.getUserListFields", userQuery);
        }
        return (List<User>) getSqlMapClientTemplate().queryForList("User.getUserList", userQuery);
    }


    public User getUserByUserNamePassWord(User user) {
        return (User) getSqlMapClientTemplate().queryForObject("User.getUserByUserNamePassWord", user);
    }

    public List<User> getUserListByUserIds(List<Integer> ids) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("keys", ids);
        return (List<User>) getSqlMapClientTemplate().queryForList("User.getUserListByUserIds", params);
    }

    public int setAllUserOffLine() {
        return getSqlMapClientTemplate().update("User.setAllUserOffLine");
    }
}