package com.cc.service;

import com.cc.pojo.Message;

import java.util.List;

/**
 * 留言业务层接口
 */
public interface MessageService {

    //查询留言列表
    List<Message> listMessage();

    //新增留言
    int addMessage(Message message);

    //删除留言
    void deleteMessage(Long id);
}
