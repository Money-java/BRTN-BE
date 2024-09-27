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


  // 3. 댓글 삽입 -->
  // 인증 커뮤니티 페이지 explore탭
  @Override
  public void insertComment(PostCommentVO comment) {
    postCommentMapper.insertComment(comment);
  }

  // 5. 댓글 수정 -->
  // 인증 커뮤니티 페이지 explore탭
  @Override
  public void updateComment(PostCommentVO comment) {
    postCommentMapper.updateComment(comment);
  }

  // 6. 댓글 삭제 -->
  // 인증 커뮤니티 페이지 explore탭
  @Override
  public void deleteComment(Long commentId) {
    postCommentMapper.deleteComment(commentId);
  }

  // 댓글 ID로 댓글 조회
  @Override
  public PostCommentVO selectCommentById(Long commentId) {
    return postCommentMapper.selectCommentById(commentId);
  }

  // 모든 댓글 조회
  @Override
  public List<PostCommentVO> selectAllComments() {
    return postCommentMapper.selectAllComments();
  }

}
