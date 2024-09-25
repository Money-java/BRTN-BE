package com.example.backend.PostComment.controller;

import com.example.backend.PostComment.service.PostCommentService;
import com.example.backend.PostComment.vo.PostCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class PostCommentController {

  private final PostCommentService postCommentService;

  @Autowired
  public PostCommentController(PostCommentService postCommentService) {
    this.postCommentService = postCommentService;
  }

  @PostMapping
  public void insertComment(@RequestBody PostCommentVO comment) {
    postCommentService.insertComment(comment);
  }

  @GetMapping("/{id}")
  public PostCommentVO selectCommentById(@PathVariable Long id) {
    return postCommentService.selectCommentById(id);
  }

  @GetMapping
  public List<PostCommentVO> selectAllComments() {
    return postCommentService.selectAllComments();
  }

  @PutMapping
  public void updateComment(@RequestBody PostCommentVO comment) {
    postCommentService.updateComment(comment);
  }

  @DeleteMapping("/{id}")
  public void deleteComment(@PathVariable Long id) {
    postCommentService.deleteComment(id);
  }
}
