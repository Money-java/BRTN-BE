package com.example.backend.PostComment.controller;

import com.example.backend.PostComment.service.PostCommentServiceImpl;
import com.example.backend.PostComment.vo.PostCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post-community/posts")
public class PostCommentController {

  private final PostCommentServiceImpl postCommentService;

  @Autowired
  public PostCommentController(PostCommentServiceImpl postCommentService) {
    this.postCommentService = postCommentService;
  }

  // 1. 댓글 조회
  @GetMapping("/{postId}/comments")
  public ResponseEntity<List<PostCommentVO>> selectCommentsByPostId(@PathVariable Long postId) {
    List<PostCommentVO> comments = postCommentService.selectCommentsByPostId(postId);
    return ResponseEntity.ok(comments);
  }


  // 3. 댓글 삽입 -->
  // 인증 커뮤니티 페이지 explore탭
  @PostMapping("/{postId}/comments")
  public void insertComment(@PathVariable Long postId, @RequestBody PostCommentVO comment) {
    // 현재 로그인한 사용자 정보 가져오기
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    String currentUsername = authentication.getName();

    // 작성자를 로그인한 사용자로 설정
//    comment.setCommentWriter(currentUsername);


    // commentWriter 클라이언트에서 전달 －＞ 설정 Ｘ
    comment.setPostId(postId); // postId를 VO에 설정
    postCommentService.insertComment(comment);
  }

  // 5. 댓글 수정 -->
  // 인증 커뮤니티 페이지 explore탭
  @PutMapping("/{postId}/comments/{commentId}")
  public void updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody PostCommentVO comment) {
    // 현재 로그인한 사용자 정보 가져오기
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    String currentUsername = authentication.getName();

    // 댓글 작성자와 현재 로그인 사용자가 일치하는지 확인
//    PostCommentVO existingComment = postCommentService.selectCommentById(commentId);

    comment.setPostId(postId); // postId를 VO에 설정
    comment.setCommentId(commentId); // commentId를 VO에 설정
//    comment.setCommentWriter(currentUsername); // 수정 시 작성자는 그대로 유지

    postCommentService.updateComment(comment);
  }

  // 6. 댓글 삭제 -->
  // 인증 커뮤니티 페이지 explore탭
  @DeleteMapping("/{postId}/comments/{commentId}")
  public void deleteComment(@PathVariable Long commentId) {
    // 현재 로그인한 사용자 정보 가져오기
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    String currentUsername = authentication.getName();

    // 댓글 작성자와 현재 로그인 사용자가 일치하는지 확인
//    PostCommentVO existingComment = postCommentService.selectCommentById(commentId);

    postCommentService.deleteComment(commentId);
  }

  // 7-1. 댓글 수 -- 특정 게시물에 대한
  @GetMapping("{postId}/comments/count")
  public ResponseEntity<Long> getCommentCnt(@PathVariable Long postId) {
    Long postCnt = postCommentService.showCommentCnt(postId);
    return ResponseEntity.ok(postCnt);
  }

  // 7-2. 댓글 수 -- 전체 게시물 별 댓글 수
  @GetMapping("/comments")
  public ResponseEntity<Map<Long, Long>> getCommentCntsForPosts() {
    List<Long> postList = postCommentService.findAllPostIds();

    Map<Long, Long> commentCounts = postCommentService.getCommentCntsForPosts(postList);
    return ResponseEntity.ok(commentCounts);
  }

  // 특정 댓글 조회
  @GetMapping("/comments/{commentId}")
  public PostCommentVO selectCommentById(@PathVariable Long commentId) {
    return postCommentService.selectCommentById(commentId);
  }

//  // 모든 댓글 조회 (선택 사항, 필요하면 유지)
//  @GetMapping("/comments")
//  public List<PostCommentVO> selectAllComments() {
//    return postCommentService.selectAllComments();
//
//  }

}
