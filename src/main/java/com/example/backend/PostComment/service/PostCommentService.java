package com.example.backend.PostComment.service;

import com.example.backend.PostComment.vo.PostCommentVO;

import java.util.List;

public interface PostCommentService {

    // 댓글 삽입
    void insertComment(PostCommentVO comment);
    // 특정 ID로 댓글 조회
    PostCommentVO selectCommentById(Long commentId);
    // 모든 댓글 조회
    List<PostCommentVO> selectAllComments();
    // 댓글 업데이트
    void updateComment(PostCommentVO comment);
    // 댓글 삭제
    void deleteComment(Long commentId);
}
