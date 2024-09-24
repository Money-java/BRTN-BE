package com.example.backend.PostComment.mapper;

import com.example.backend.PostComment.vo.PostCommentVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PostCommentMapper {
  void insertComment(PostCommentVO comment);
  PostCommentVO selectCommentById(Long commentId);
  List<PostCommentVO> selectAllComments();
  void updateComment(PostCommentVO comment);
  void deleteComment(Long commentId);
}
