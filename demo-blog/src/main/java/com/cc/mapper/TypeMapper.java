package com.cc.mapper;

import com.cc.pojo.Type;

import java.util.List;

public interface TypeMapper {
    //新增分类
    int saveType(Type type);

    //根据id获取分类
    Type getType(Long id);

    //根据分类的名称获取分类
    Type getTypeByName(String name);

    //获取所有的分类
    List<Type> getAllType();

    //首页右侧展示type对应的博客数量
    List<Type> getBlogType();

    //修改分类
    int updateType(Type type);

    //删除分类
    int  deleteType(Long id);
}
