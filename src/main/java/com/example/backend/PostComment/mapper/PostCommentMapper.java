package com.example.backend.PostComment.mapper;

import com.example.backend.PostComment.vo.PostCommentVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PostCommentMapper {

  // 1. 댓글 조회 -- 특정 post에 대한 댓글 전체 조회
  List<PostCommentVO> selectCommentsByPostId(Long postId);

  // 3. 댓글 삽입 -->
  // 인증 커뮤니티 페이지 explore탭
  void insertComment(PostCommentVO comment);

  // 5. 댓글 수정 -->
  // 인증 커뮤니티 페이지 explore탭
  void updateComment(PostCommentVO comment);

  // 6. 댓글 삭제 -->
  // 인증 커뮤니티 페이지 explore탭
  void deleteComment(Long commentId);

  // 7-1. 댓글 수 확인
  Long showCommentCnt(Long postId);

  // 8. 전체 게시물 ID를 조회 -> 리스트로
  List<Long> findAllPostIds();

  // commentId로 댓글 조회
  PostCommentVO selectCommentById(Long commentId);

//  // 모든 댓글 조회
//  List<PostCommentVO> selectAllComments();



}
