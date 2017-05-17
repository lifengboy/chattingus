package com.chattingus.commons.util;

import com.chattingus.domain.Friend;
import com.chattingus.domain.User;

import java.util.*;

/**
 * Created by 002116110445 on 2017/5/7.
 */
public class TransformUtil {

    public static List<Friend> TranformFromUsersToFriends(List<Friend> friends, List<User> users){
        Map<Integer, User> userMap = TranformFromUserListToMap(users);
        Map<Integer, Friend> friendMap = TranformFromFriendListToMap(friends);
        Set<Map.Entry<Integer, Friend>> entries = friendMap.entrySet();
        Iterator<Map.Entry<Integer, Friend>> it = entries.iterator();
        while(it.hasNext()){
            Map.Entry<Integer, Friend> entry = it.next();
            Integer key = entry.getKey();
            Friend f = entry.getValue();
            f.setUser(userMap.get(key));
        }
        return friends;
    }

    public static Map<Integer, User> TranformFromUserListToMap(List<User> list){
        Map<Integer, User> map = new HashMap<Integer, User>();
        if(list != null && list.size() > 0){
            for (User user : list){
                map.put(user.getUserId(), user);
            }
        }
        return map;
    }

    public static Map<Integer, Friend> TranformFromFriendListToMap(List<Friend> list){
        Map<Integer, Friend> map = new HashMap<Integer, Friend>();
        if(list != null && list.size() > 0){
            for (Friend friend : list){
                map.put(friend.getFriendId(), friend);
            }
        }
        return map;
    }

}
