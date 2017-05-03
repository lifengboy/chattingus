package com.chattingus.dao;

import com.chattingus.domain.Friend;
import com.chattingus.query.FriendQuery;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author
 * @date 2017-04-18
 */

@Repository
public class FriendDAO extends CobarClientBaseDao {


    /**
     * 添加
     */
    public Integer addFriend(Friend friend) {
        return (Integer) getSqlMapClientTemplate().insert("Friend.insertFriend", friend);
    }

    /**
     * 根据主键查找
     */
    public Friend getFriendByKey(Integer id) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("id", id);
        Friend result = (Friend) getSqlMapClientTemplate().queryForObject(
                "Friend.getFriendByKey", params);
        return result;
    }

    /**
     * 根据userId和friendId查询
     */
    public Friend getFriendByUserIdFriendId(Integer userId, Integer friendId) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("userId", userId);
        params.put("friendId", friendId);
        Friend result = (Friend) getSqlMapClientTemplate().queryForObject(
                "Friend.getFriendByUserIdFriendId", params);
        return result;
    }

    /**
     * 根据主键批量查找
     */
    public List<Friend> getFriendByKeys(List<Integer> idList) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("keys", idList);
        return (List<Friend>) getSqlMapClientTemplate().queryForList("Friend.getFriendsByKeys", params);
    }

    /**
     * 根据主键删除
     */
    public Integer deleteByKey(Integer id) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("id", id);
        Integer row = (Integer) getSqlMapClientTemplate().delete("Friend.deleteByKey", params);
        return row;
    }

    /**
     * 根据主键批量删除
     */
    public Integer deleteByKeys(List<Integer> idList) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("keys", idList);
        Integer row = (Integer) getSqlMapClientTemplate().delete("Friend.deleteByKeys", params);
        return row;
    }

    /**
     * 根据主键更新
     */
    public Integer updateFriendByKey(Friend friend) {
        return (Integer) getSqlMapClientTemplate().update("Friend.updateFriendByKey", friend);
    }

    /**
     * 根据条件查询分页查询
     */
    @SuppressWarnings("unchecked")
    public Result<Friend> getFriendListWithPage(FriendQuery friendQuery) {
        Result<Friend> rs = new Result<Friend>();
        rs.setCount((Integer) getSqlMapClientTemplate().queryForObject("Friend.getFriendListCount", friendQuery));
        if (friendQuery.getFields() != null && friendQuery.getFields() != "") {
            rs.setList((List<Friend>) getSqlMapClientTemplate().queryForList("Friend.getFriendListWithPageFields", friendQuery));
        } else {
            rs.setList((List<Friend>) getSqlMapClientTemplate().queryForList("Friend.getFriendListWithPage", friendQuery));
        }
        return rs;
    }

    /**
     * 根据条件查询
     */
    @SuppressWarnings("unchecked")
    public List<Friend> getFriendList(FriendQuery friendQuery) {
        if (friendQuery.getFields() != null && friendQuery.getFields() != "") {
            return (List<Friend>) getSqlMapClientTemplate().queryForList("Friend.getFriendListFields", friendQuery);
        }
        return (List<Friend>) getSqlMapClientTemplate().queryForList("Friend.getFriendList", friendQuery);
    }


}