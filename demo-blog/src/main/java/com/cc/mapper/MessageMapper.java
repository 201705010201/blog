package com.cc.mapper;

import com.cc.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 留言持久层接口
 */
public interface MessageMapper {

    //查询父级留言
    List<Message> findByParentIdNull(@Param("parentId") Long parentId);

    //查询一级回复
    List<Message> findByParentIdNotNull(@Param("id") Long id);

    //查询二级以及所有子集回复
    List<Message> findByReplayId(@Param("childId") Long childId);

    //新增留言
    int addMessage(Message message);

    //删除留言
    void deleteMessage(Long id);

}
