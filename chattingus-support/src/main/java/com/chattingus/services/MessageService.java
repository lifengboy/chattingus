package com.chattingus.services;

import com.chattingus.dao.MessageDAO;
import com.chattingus.dao.Result;
import com.chattingus.domain.Message;
import com.chattingus.domain.User;
import com.chattingus.query.MessageQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author
 * @since 2017-04-24
 */
@Service
public class MessageService {

    @Resource
    MessageDAO messageDAO;

    /**
     * 插入
     */
    public Integer addMessage(Message message) {
        return messageDAO.addMessage(message);
    }

    /**
     * 根据主键查找
     */
    public Message getMessageByKey(Integer mId) {
        return messageDAO.getMessageByKey(mId);
    }

    /**
     * 根据主键查找
     */
    public Message getMessageByKey(User user, Integer mId ) {
        return messageDAO.getMessageByKey(mId);
    }


    /**
     * 根据主键组进行查找
     */
    public List<Message> getMessageByKeys(User user, List<Integer> mIdList) {
        return messageDAO.getMessageByKeys(mIdList);
    }

    /**
     * 根据主键组进行查找
     */
    public List<Message> getMessageByKeys(List<Integer> mIdList) {
        return messageDAO.getMessageByKeys(mIdList);
    }

    /**
     * 根据主键删除
     */
    public Integer deleteByKey(User user, Integer mId) {
        return messageDAO.deleteByKey(mId);
    }

    /**
     * 根据主键组进行删除
     */
    public Integer deleteByKeys(User user, List<Integer> mIdList) {
        return messageDAO.deleteByKeys(mIdList);
    }

    /**
     * 根据主键更新
     */
    public Integer updateMessageByKey(User user, Message message) {
        return messageDAO.updateMessageByKey(message);
    }

    /**
     * 根据条件查询分页查询
     */
    public Result<Message> getMessageListWithPage(User user, MessageQuery messageQuery) {
        return messageDAO.getMessageListWithPage(messageQuery);
    }

    /**
     * 根据条件查询
     */
    public List<Message> getMessageList(MessageQuery messageQuery) {
        return messageDAO.getMessageList(messageQuery);
    }

    public List<Message> getMessageByType(int userId, int typeNumber) {
        return messageDAO.getMessageByType(userId, typeNumber);
    }
}
