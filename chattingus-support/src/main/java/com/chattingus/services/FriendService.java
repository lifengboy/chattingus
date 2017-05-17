package com.chattingus.services;

import com.chattingus.commons.util.TransformUtil;
import com.chattingus.dao.FriendDAO;
import com.chattingus.query.FriendQuery;
import com.chattingus.domain.Friend;
import com.chattingus.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 
 * @since 2017-04-07
 */
@Service
public class FriendService {

    @Resource
    FriendDAO friendDAO;

    @Resource
    UserService userService;

    /**
     * 插入
     */
    public Integer addFriend(Friend friend) {
        return friendDAO.addFriend(friend);
    }
            
    /**
     * 根据主键查找
    */
    public Friend getFriendByKey(Integer id) {
        return friendDAO.getFriendByKey(id);
    }

    /**
     * 根据userId和friendId查询
     */
    public Friend getFriendByUserIdFriendId(Integer userId, Integer friendId) {
        return friendDAO.getFriendByUserIdFriendId(userId, friendId);
    }

    /**
     * 根据主键查找
    */
    public Friend getFriendByKey(User user, Integer id) {
        return friendDAO.getFriendByKey(id);
    }

        
    /**
     * 根据主键组进行查找
    */
    public List<Friend> getFriendByKeys(User user,List<Integer> idList) {
        return friendDAO.getFriendByKeys(idList);
    }

    /**
     * 根据主键组进行查找
    */
    public List<Friend> getFriendByKeys(List<Integer> idList) {
        return friendDAO.getFriendByKeys(idList);
    }
        
    /**
     * 根据主键删除
     */
    public Integer deleteByKey(User user, Integer id) {
        return friendDAO.deleteByKey(id);
    }

    /**
     * 根据主键组进行删除
     */
    public Integer deleteByKeys(User user, List<Integer> idList) {
        return friendDAO.deleteByKeys(idList);
    }
    /**
     * 根据主键更新
     */
    public Integer updateFriendByKey(User user, Friend friend) {
        return friendDAO.updateFriendByKey(friend);
    }

    /**
     * 根据条件查询分页查询
     */
//    public Result<Friend> getFriendListWithPage(User user, FriendQuery friendQuery) {
//        return friendDAO.getFriendListWithPage(friendQuery);
//    }
    /**
     * 根据条件查询
     */
    public List<Friend> getFriendList(FriendQuery friendQuery) {
        return friendDAO.getFriendList(friendQuery);
    }

    /**
     * 根据条件查询
     */
    public List<Friend> getFriendListByUserId(User user) {
        List<Friend> friendsTemp = friendDAO.getFriendListByUserId(user);
        List<Integer> ids = new ArrayList<Integer>();
        for(Friend friend : friendsTemp){
            ids.add(friend.getFriendId());
        }
        List<User> users = userService.getUserListByUserIds(ids);
        List<Friend> returnFriends = TransformUtil.TranformFromUsersToFriends(friendsTemp, users);
        return returnFriends;
    }

}
