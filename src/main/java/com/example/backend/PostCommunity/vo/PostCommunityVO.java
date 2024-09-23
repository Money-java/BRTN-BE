package com.example.backend.PostCommunity.vo;

import lombok.Data;

@Data
public class PostCommunityVO {
  private Long postId;
  private Long userId;
  private Long habitId;
  private int postLikes;
  private String imageURL;
  private String content;
  private String hashtag;
  private String createdAt;
}
