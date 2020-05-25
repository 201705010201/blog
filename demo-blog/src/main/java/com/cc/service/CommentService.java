package com.cc.service;

import com.cc.pojo.Comment;

import java.util.List;

public interface CommentService {


  List<Comment> listCommentByBlogId(Long blogId);

  int saveComment(Comment comment);

//    查询子评论
//    List<Comment> getChildComment(Long blogId,Long id);

  //删除评论
  void deleteComment(Comment comment,Long id);

}
