package com.chattingus.dao;

import com.chattingus.domain.Message;
import com.chattingus.query.MessageQuery;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @date 2017-04-24
 */
@Repository
public class MessageDAO extends CobarClientBaseDao {

    /**
     * 添加
     */
    public Integer addMessage(Message message) {
        return (Integer) getSqlMapClientTemplate().insert("Message.insertMessage", message);
    }

    /**
     * 根据主键查找
     */
    public Message getMessageByKey(Integer mId) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("mId", mId);
        Message result = (Message) getSqlMapClientTemplate().queryForObject(
                "Message.getMessageByKey", params);
        return result;
    }

    /**
     * 根据主键批量查找
     */
    public List<Message> getMessageByKeys(List<Integer> mIdList) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("keys", mIdList);
        return (List<Message>) getSqlMapClientTemplate().queryForList("Message.getMessagesByKeys", params);
    }

    /**
     * 根据主键删除
     */
    public Integer deleteByKey(Integer mId) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("mId", mId);
        Integer row = (Integer) getSqlMapClientTemplate().delete("Message.deleteByKey", params);
        return row;
    }

    /**
     * 根据主键批量删除
     */
    public Integer deleteByKeys(List<Integer> mIdList) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("keys", mIdList);
        Integer row = (Integer) getSqlMapClientTemplate().delete("Message.deleteByKeys", params);
        return row;
    }

    /**
     * 根据主键更新
     */
    public Integer updateMessageByKey(Message message) {
        return (Integer) getSqlMapClientTemplate().update("Message.updateMessageByKey", message);
    }

    /**
     * 根据条件查询分页查询
     */
    @SuppressWarnings("unchecked")
    public Result<Message> getMessageListWithPage(MessageQuery messageQuery) {
        Result<Message> rs = new Result<Message>();
        rs.setCount((Integer) getSqlMapClientTemplate().queryForObject("Message.getMessageListCount", messageQuery));
        if (messageQuery.getFields() != null && messageQuery.getFields() != "") {
            rs.setList((List<Message>) getSqlMapClientTemplate().queryForList("Message.getMessageListWithPageFields", messageQuery));
        } else {
            rs.setList((List<Message>) getSqlMapClientTemplate().queryForList("Message.getMessageListWithPage", messageQuery));
        }
        return rs;
    }

    /**
     * 根据条件查询
     */
    @SuppressWarnings("unchecked")
    public List<Message> getMessageList(MessageQuery messageQuery) {
        if (messageQuery.getFields() != null && messageQuery.getFields() != "") {
            return (List<Message>) getSqlMapClientTemplate().queryForList("Message.getMessageListFields", messageQuery);
        }
        return (List<Message>) getSqlMapClientTemplate().queryForList("Message.getMessageList", messageQuery);
    }


    public List<Message> getMessageByType(int userId, int typeNumber) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("toUserId", userId);
        params.put("messageType", typeNumber);
        return (List<Message>) getSqlMapClientTemplate().queryForList("Message.getMessageByType", params);
    }
}