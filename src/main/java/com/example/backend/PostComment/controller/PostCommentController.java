package com.example.backend.PostComment.controller;

import com.example.backend.PostComment.service.PostCommentServiceImpl;
import com.example.backend.PostComment.vo.PostCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-community/posts")
public class PostCommentController {

  private final PostCommentServiceImpl postCommentService;

  @Autowired
  public PostCommentController(PostCommentServiceImpl postCommentService) {
    this.postCommentService = postCommentService;
  }

  // 3. 댓글 삽입 -->
  // 인증 커뮤니티 페이지 explore탭
  @PostMapping("/{postId}/comments")
  public void insertComment(@PathVariable Long postId, @RequestBody PostCommentVO comment) {
    comment.setPostId(postId); // postId를 VO에 설정
    postCommentService.insertComment(comment);
  }

  // 5. 댓글 수정 -->
  // 인증 커뮤니티 페이지 explore탭
  @PutMapping("/{postId}/comments/{commentId}")
  public void updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody PostCommentVO comment) {
    comment.setPostId(postId); // postId를 VO에 설정
    comment.setCommentId(commentId); // commentId를 VO에 설정
    postCommentService.updateComment(comment);
  }

  // 6. 댓글 삭제 -->
  // 인증 커뮤니티 페이지 explore탭
  @DeleteMapping("/{postId}/comments/{commentId}")
  public void deleteComment(@PathVariable Long commentId) {
    postCommentService.deleteComment(commentId);
  }

  // 특정 댓글 조회
  @GetMapping("/comments/{commentId}")
  public PostCommentVO selectCommentById(@PathVariable Long commentId) {
    return postCommentService.selectCommentById(commentId);
  }

  // 모든 댓글 조회 (선택 사항, 필요하면 유지)
  @GetMapping("/comments")
  public List<PostCommentVO> selectAllComments() {
    return postCommentService.selectAllComments();
  }

}
