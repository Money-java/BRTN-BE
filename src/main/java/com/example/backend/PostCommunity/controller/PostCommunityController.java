package com.example.backend.PostCommunity.controller;

import com.example.backend.PostCommunity.service.PostCommunityService;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostCommunityController {

  private final PostCommunityService postCommunityService;

  @Autowired
  public PostCommunityController(PostCommunityService postCommunityService) {
    this.postCommunityService = postCommunityService;
  }

  @PostMapping
  public void insertPost(@RequestBody PostCommunityVO post) {
    postCommunityService.insertPost(post);
  }

  @GetMapping("/{id}")
  public PostCommunityVO selectPostById(@PathVariable Long id) {
    return postCommunityService.selectPostById(id);
  }

  @GetMapping
  public List<PostCommunityVO> selectAllPosts() {
    return postCommunityService.selectAllPosts();
  }

  @PutMapping
  public void updatePost(@RequestBody PostCommunityVO post) {
    postCommunityService.updatePost(post);
  }

  @DeleteMapping("/{id}")
  public void deletePost(@PathVariable Long id) {
    postCommunityService.deletePost(id);
  }
}
