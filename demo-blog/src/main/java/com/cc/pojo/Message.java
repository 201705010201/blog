package com.cc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Negative;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 留言实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
     private Long id;
     private String nickname;
     private String email;
     private String content;
     private String avatar;
     private Date createTime;
     private Long parentMessageId;
     private boolean adminMessage;

     //回复留言
     private List<Message> replyMessages = new ArrayList<>();
     private Message parentMessage;
     private String parentNickname;
}
