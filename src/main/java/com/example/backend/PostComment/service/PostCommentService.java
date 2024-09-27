package com.example.backend.PostComment.service;

import com.example.backend.PostComment.vo.PostCommentVO;

import java.util.List;

public interface PostCommentService {
  // 3. 댓글 삽입 -->
  // 인증 커뮤니티 페이지 explore탭
  void insertComment(PostCommentVO comment);

  // 5. 댓글 수정 -->
  // 인증 커뮤니티 페이지 explore탭
  void updateComment(PostCommentVO comment);

  // 6. 댓글 삭제 -->
  // 인증 커뮤니티 페이지 explore탭
  void deleteComment(Long commentId);

  // 댓글 ID로 댓글 조회
  PostCommentVO selectCommentById(Long commentId);

  // 모든 댓글 조회
  List<PostCommentVO> selectAllComments();

}
