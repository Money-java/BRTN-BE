package com.example.backend.PostLikes.controller;

import com.example.backend.PostLikes.vo.PostLikesVO;
import com.example.backend.PostLikes.service.PostLikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-likes")
public class PostLikesController {

  @Autowired
  private PostLikesService postLikesService;

  // 게시물에 좋아요 추가
  @PostMapping
  public ResponseEntity<String> likePost(@RequestBody PostLikesVO postLikesVO) {
    postLikesService.addPostLike(postLikesVO);
    return new ResponseEntity<>("Post liked successfully!", HttpStatus.CREATED);
  }

  // 특정 사용자가 좋아요한 게시물 목록 조회
  @GetMapping("/{userId}")
  public ResponseEntity<List<PostLikesVO>> getPostLikesByUser(@PathVariable Long userId) {
    List<PostLikesVO> likes = postLikesService.getPostLikesByUser(userId);
    return new ResponseEntity<>(likes, HttpStatus.OK);
  }
}
