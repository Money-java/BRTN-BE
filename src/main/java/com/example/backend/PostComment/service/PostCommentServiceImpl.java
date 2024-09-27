package com.example.backend.PostComment.service;

import com.example.backend.PostComment.mapper.PostCommentMapper;
import com.example.backend.PostComment.vo.PostCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommentServiceImpl implements PostCommentService {

  private final PostCommentMapper postCommentMapper;

  @Autowired
  public PostCommentServiceImpl(PostCommentMapper postCommentMapper) {
    this.postCommentMapper = postCommentMapper;
  }

  // 댓글 삽입
  public void insertComment(PostCommentVO comment) {
    postCommentMapper.insertComment(comment);
  }

  // 특정 ID로 댓글 조회
  public PostCommentVO selectCommentById(Long commentId) {
    return postCommentMapper.selectCommentById(commentId);
  }

  // 모든 댓글 조회
  public List<PostCommentVO> selectAllComments() {
    return postCommentMapper.selectAllComments();
  }

  // 댓글 업데이트
  public void updateComment(PostCommentVO comment) {
    postCommentMapper.updateComment(comment);
  }

  // 댓글 삭제
  public void deleteComment(Long commentId) {
    postCommentMapper.deleteComment(commentId);
  }
}
