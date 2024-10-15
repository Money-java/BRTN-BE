package com.example.backend.PostLikes.vo;

import java.util.Date;
import lombok.Data;

@Data
public class PostLikesVO {
  private Long likeId;
  private Long userId;
  private Long communityId;
  private Date createdAt;

}
