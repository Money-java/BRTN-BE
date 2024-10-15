package com.example.backend.PostComment.service;

import com.example.backend.PostComment.mapper.PostCommentMapper;
import com.example.backend.PostComment.vo.PostCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostCommentServiceImpl implements PostCommentService {

  private final PostCommentMapper postCommentMapper;

  @Autowired
  public PostCommentServiceImpl(PostCommentMapper postCommentMapper) {
    this.postCommentMapper = postCommentMapper;
  }

  // 1. 댓글 조회 -- 특정 post에 대한 댓글 전체 조회
  @Override
  public List<PostCommentVO> selectCommentsByPostId(Long postId) {
    return postCommentMapper.selectCommentsByPostId(postId);
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

  // 7-1. 댓글 수 확인 -- 전체 게시물 별 댓글
  @Override
  public Map<Long, Long> getCommentCntsForPosts(List<Long> postIds) {
    Map<Long, Long> commentCounts = new HashMap<>();

//    List<Long> postList = findAllPostIds();

    for (Long postId : postIds) {
      Long count = showCommentCnt(postId);
      commentCounts.put(postId, count);
    }

    return commentCounts;
  }


  // 7-2. 게시물 하나의 댓글 수 확인
  @Override
  public Long showCommentCnt(Long postId) {
    return postCommentMapper.showCommentCnt(postId);
  }

  // 8. 전체 post id들 조회해서 리스트로
  @Override
  public List<Long> findAllPostIds() {
    return postCommentMapper.findAllPostIds();
  }

  // 댓글 ID로 댓글 조회
  @Override
  public PostCommentVO selectCommentById(Long commentId) {
    return postCommentMapper.selectCommentById(commentId);
  }





//  // 모든 댓글 조회
//  @Override
//  public List<PostCommentVO> selectAllComments() {
//    return postCommentMapper.selectAllComments();
//  }

}
