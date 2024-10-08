package com.example.backend.PostCommunity.service;

import com.example.backend.PostCommunity.dto.PostCommunityRequestDTO;
import com.example.backend.PostCommunity.vo.PostCommunityVO;

import java.util.List;

public interface PostCommunityService {
  // 4. 인증커뮤니티 인증한 습관리스트 조회
  // 인증커뮤니티 페이지의 My Shots탭, Explore탭에서 사용
  List<PostCommunityVO> selectPostsByCategory(Long userId, String categoryTitle);

  // 9. 인증개수 조회
  // 특정 사용자가 인증한 게시글의 총 개수를 구하는 메서드
  // 인증커뮤니티 페이지 (레벨)
  int countUserCertifications(Long userId);

  void insertPost(PostCommunityRequestDTO requestDTO);
  PostCommunityVO selectPostById(Long postId);
  List<PostCommunityVO> selectAllPosts();
  void updatePost(PostCommunityVO postCommunityVO);
  void deletePost(Long postId);

}
