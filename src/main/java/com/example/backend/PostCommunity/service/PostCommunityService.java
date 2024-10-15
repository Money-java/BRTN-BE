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

  int insertPost(PostCommunityRequestDTO requestDTO);
  PostCommunityVO selectPostById(Long postId);
  List<PostCommunityVO> selectAllPosts();
  void updatePost(PostCommunityVO postCommunityVO);
  void deletePost(Long postId);

  // 진행 중인 루틴 가져오기
  List<PostCommunityVO> getActiveHabitByUserId(Long userId);



  // 특정 달에 맞는 이미지 가져오기
  List<PostCommunityVO> getHabitImagesByMonth(Long userId, int month, int year, Long habitId);

  // 날짜별로 좋아요가 가장 많은 이미지를 가져오는 서비스 메서드
  List<PostCommunityVO> getMostLikedImagesByDate(Long userId, int month, int year);

  // 날짜별로 좋아요가 가장 많은 이미지를 가져오는 서비스 메서드
  PostCommunityVO getMostLikedImagesByDate2(Long userId, int year, int month, int dt);

}
