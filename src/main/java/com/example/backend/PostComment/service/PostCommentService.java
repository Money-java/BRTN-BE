package com.example.backend.PostComment.service;

import com.example.backend.PostComment.vo.PostCommentVO;

import java.util.List;
import java.util.Map;

public interface PostCommentService {

  // 1. 댓글 조회 -- 특정 post에 대한 댓글 전체 조회
  List<PostCommentVO> selectCommentsByPostId(Long PostId);

  // 3. 댓글 삽입 -->
  // 인증 커뮤니티 페이지 explore탭
  void insertComment(PostCommentVO comment);

  // 5. 댓글 수정 -->
  // 인증 커뮤니티 페이지 explore탭
  void updateComment(PostCommentVO comment);

  // 6. 댓글 삭제 -->
  // 인증 커뮤니티 페이지 explore탭
  void deleteComment(Long commentId);

  // 7. 댓글 수 확인
  // 7-1. 전체 게시물
  Map<Long, Long> getCommentCntsForPosts(List<Long> postIds);

  // 7-2. 게시물 하나 당
  Long showCommentCnt(Long postId);

  // 8. 전체 post id들 조회해서 리스트로
  List<Long> findAllPostIds();


  // 댓글 ID로 댓글 조회
  PostCommentVO selectCommentById(Long commentId);

//  // 모든 댓글 조회
//  List<PostCommentVO> selectAllComments();

}
