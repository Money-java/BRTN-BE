package com.example.backend.PostCommunity.service;

import com.example.backend.PostCommunity.vo.PostCommunityVO;
import java.util.List;

public interface PostCommunityService {
  // 4. 인증커뮤니티 인증한 습관리스트 조회
  // 인증커뮤니티 페이지의 My Shots탭, Explore탭에서 사용
  List<PostCommunityVO> selectPostsByCategory(Long userId, String categoryTitle);

  void insertPost(PostCommunityVO postCommunityVO);
  PostCommunityVO selectPostById(Long postId);
  List<PostCommunityVO> selectAllPosts();
  void updatePost(PostCommunityVO postCommunityVO);
  void deletePost(Long postId);


}
