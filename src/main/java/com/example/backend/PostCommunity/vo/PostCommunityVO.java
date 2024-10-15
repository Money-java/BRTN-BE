package com.example.backend.PostCommunity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class PostCommunityVO {
  private Long postId;
  private Long userId;
  private Long habitId;
  private int postLikes;
  private String imageURL;
  private String content;
  private String hashtag;
  private Date createdAt;
  private Long myHabitId;
  private String state;
  private String habitTitle;
  private String  categoryTitle;
}
