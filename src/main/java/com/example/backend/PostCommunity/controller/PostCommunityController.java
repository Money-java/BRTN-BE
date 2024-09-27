package com.example.backend.PostCommunity.controller;

import com.example.backend.PostCommunity.service.PostCommunityService;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post-community")
public class PostCommunityController {

  private final PostCommunityService postCommunityService;

  @Autowired
  public PostCommunityController(PostCommunityService postCommunityService) {
    this.postCommunityService = postCommunityService;
  }

  // 4. 인증커뮤니티 인증한 습관리스트 조회
  // 인증커뮤니티 페이지의 My Shots탭, Explore탭에서 사용
  @GetMapping("/explore")
  public List<PostCommunityVO> selectPostsByCategory(@RequestParam(value = "userId", required = false) Long userId,
                                                     @RequestParam(value = "category", required = false) String categoryTitle) {
    return postCommunityService.selectPostsByCategory(userId, categoryTitle);
  }

  // Post 추가
  @PostMapping("/add")
  public void insertPost(@RequestBody PostCommunityVO postCommunityVO) {
    postCommunityService.insertPost(postCommunityVO);
  }

  // 특정 Post 조회 (by ID)
  @GetMapping("/{postId}")
  public PostCommunityVO selectPostById(@PathVariable Long postId) {
    return postCommunityService.selectPostById(postId);
  }

  // 모든 Post 조회
  @GetMapping("/all")
  public List<PostCommunityVO> selectAllPosts() {
    return postCommunityService.selectAllPosts();
  }

  // Post 업데이트
  @PutMapping("/update/{postId}")
  public void updatePost(@PathVariable Long postId, @RequestBody PostCommunityVO postCommunityVO) {
    postCommunityVO.setPostId(postId);
    postCommunityService.updatePost(postCommunityVO);
  }

  // Post 삭제
  @DeleteMapping("/delete/{postId}")
  public void deletePost(@PathVariable Long postId) {
    postCommunityService.deletePost(postId);
  }


}
