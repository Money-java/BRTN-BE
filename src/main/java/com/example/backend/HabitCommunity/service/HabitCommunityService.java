package com.example.backend.HabitCommunity.service;

import com.example.backend.HabitCommunity.vo.HabitCommunityVO;
import java.util.List;

public interface HabitCommunityService {

  // 1. 습관 리스트 조회 (동적 정렬: 최신순, 좋아요순, 참여자순, 달성자순)
  List<HabitCommunityVO> selectHabitList(String categoryName, String sortType);

  // 2. 내가 좋아요한 루틴 조회
  List<HabitCommunityVO> selectLikedCommunities(String userId);

  // 특정 ID로 HabitCommunity 조회
  HabitCommunityVO selectHabitCommunityById(Long communityId);

  // 모든 HabitCommunity 조회
  List<HabitCommunityVO> selectAllHabitCommunities();

  // HabitCommunity 업데이트
  void updateHabitCommunity(HabitCommunityVO habitCommunity);

  // HabitCommunity 삭제
  void deleteHabitCommunity(Long communityId);


}
