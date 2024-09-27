package com.example.backend.PostCommunity.controller;

import com.example.backend.PostCommunity.service.PostCommunityServiceImpl;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostCommunityController {

  private final PostCommunityServiceImpl postCommunityServiceImpl;

  @Autowired
  public PostCommunityController(PostCommunityServiceImpl postCommunityServiceImpl) {
    this.postCommunityServiceImpl = postCommunityServiceImpl;
  }

  @PostMapping
  public void insertPost(@RequestBody PostCommunityVO post) {
    postCommunityServiceImpl.insertPost(post);
  }

  @GetMapping("/{id}")
  public PostCommunityVO selectPostById(@PathVariable Long id) {
    return postCommunityServiceImpl.selectPostById(id);
  }

  @GetMapping
  public List<PostCommunityVO> selectAllPosts() {
    return postCommunityServiceImpl.selectAllPosts();
  }

  @PutMapping
  public void updatePost(@RequestBody PostCommunityVO post) {
    postCommunityServiceImpl.updatePost(post);
  }

  @DeleteMapping("/{id}")
  public void deletePost(@PathVariable Long id) {
    postCommunityServiceImpl.deletePost(id);
  }
}
