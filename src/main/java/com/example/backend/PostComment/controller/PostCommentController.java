package com.example.backend.PostComment.controller;

import com.example.backend.PostComment.service.PostCommentServiceImpl;
import com.example.backend.PostComment.vo.PostCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class PostCommentController {

  private final PostCommentServiceImpl postCommentServiceImpl;

  @Autowired
  public PostCommentController(PostCommentServiceImpl postCommentServiceImpl) {
    this.postCommentServiceImpl = postCommentServiceImpl;
  }

  @PostMapping
  public void insertComment(@RequestBody PostCommentVO comment) {
    postCommentServiceImpl.insertComment(comment);
  }

  @GetMapping("/{id}")
  public PostCommentVO selectCommentById(@PathVariable Long id) {
    return postCommentServiceImpl.selectCommentById(id);
  }

  @GetMapping
  public List<PostCommentVO> selectAllComments() {
    return postCommentServiceImpl.selectAllComments();
  }

  @PutMapping
  public void updateComment(@RequestBody PostCommentVO comment) {
    postCommentServiceImpl.updateComment(comment);
  }

  @DeleteMapping("/{id}")
  public void deleteComment(@PathVariable Long id) {
    postCommentServiceImpl.deleteComment(id);
  }
}
