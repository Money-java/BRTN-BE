package com.example.backend.HabitCommunity.service;

import com.example.backend.HabitCommunity.vo.HabitCommunityVO;
import java.util.List;

public interface HabitCommunityService {

  // 1. 습관 리스트 조회 (동적 정렬: 최신순, 좋아요순, 참여자순, 달성자순)
  List<HabitCommunityVO> selectHabitList(String categoryName, String sortType);

  // 2. 내가 좋아요한 루틴 조회
  List<HabitCommunityVO> selectLikedCommunities(String userId);

  // 7. 도전하기 기능(스크랩)
  void addHabitToMyHabit(Long userId, Long habitId);

  // 8. 습관검색기능
  List<HabitCommunityVO> searchHabitCommunities(String categoryName, String sortType,String keyword);

  // HabitCommunity 삽입
  void insertHabitCommunity(HabitCommunityVO habitCommunity);

  // 특정 ID로 HabitCommunity 조회
  HabitCommunityVO selectHabitCommunityById(Long communityId);

  // 모든 HabitCommunity 조회
  List<HabitCommunityVO> selectAllHabitCommunities();

  // HabitCommunity 업데이트
  void updateHabitCommunity(HabitCommunityVO habitCommunity);

  // HabitCommunity 삭제
  void deleteHabitCommunity(Long communityId);

  // 좋아요 추가
  void addLike(Long userId, Long communityId);
  // 좋아요 삭제
  void removeLike(Long userId, Long communityId);


}
