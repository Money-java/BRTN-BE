package com.example.backend.PostComment.vo;

import lombok.Data;

import java.util.Date;

@Data
public class PostCommentVO {
  private Long commentId;
  private Long postId;
  private String commentWriter;
  private String commentContent;
  private String createdAt;
}
