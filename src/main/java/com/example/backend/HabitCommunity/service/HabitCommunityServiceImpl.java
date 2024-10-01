package com.example.backend.HabitCommunity.service;

import com.example.backend.HabitCommunity.mapper.HabitCommunityMapper;
import com.example.backend.HabitCommunity.vo.HabitCommunityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitCommunityServiceImpl implements HabitCommunityService {

  private final HabitCommunityMapper habitCommunityMapper;

  @Autowired
  public HabitCommunityServiceImpl(HabitCommunityMapper habitCommunityMapper) {
    this.habitCommunityMapper = habitCommunityMapper;
  }

  // 1. 습관 리스트 조회 (동적 정렬: 최신순, 좋아요순, 참여자순, 달성자순)
  @Override
  public List<HabitCommunityVO> selectHabitList(String categoryName, String sortType) {
    return habitCommunityMapper.selectHabitList(categoryName, sortType);
  }

  // 2. 습관리스트 조회2
  // 내가 좋아요한 루틴 조회
  @Override
  public List<HabitCommunityVO> selectLikedCommunities(String userId) {
    return habitCommunityMapper.selectLikedCommunities(userId);
  }


   // 7. 도전하기 기능(스크랩)
   // 다른 사람이 공유한 습관을 보고 도전하기 버튼을 누르면 나의 습관으로 추가
   // 루틴공유커뮤니티 페이지
  @Override
  public void addHabitToMyHabit(Long userId, Long habitId) {
    habitCommunityMapper.addHabitToMyHabit(userId, habitId);
  }

  // 8. 습관검색기능
  // 루틴커뮤니티 페이지
  @Override
  public List<HabitCommunityVO> searchHabitCommunities(String keyword) {
    return habitCommunityMapper.searchHabitCommunities(keyword);
  }


  // HabitCommunity 삽입
  @Override
  public void insertHabitCommunity(HabitCommunityVO habitCommunity) {
    habitCommunityMapper.insertHabitCommunity(habitCommunity);
  }

  // 특정 ID로 HabitCommunity 조회

  @Override
  public HabitCommunityVO selectHabitCommunityById(Long communityId) {
    return habitCommunityMapper.selectHabitCommunityById(communityId);
  }

  // 모든 HabitCommunity 조회
  @Override
  public List<HabitCommunityVO> selectAllHabitCommunities() {
    return habitCommunityMapper.selectAllHabitCommunities();
  }

  // HabitCommunity 업데이트

  @Override
  public void updateHabitCommunity(HabitCommunityVO habitCommunity) {
    habitCommunityMapper.updateHabitCommunity(habitCommunity);
  }

  // HabitCommunity 삭제

  @Override
  public void deleteHabitCommunity(Long communityId) {
    habitCommunityMapper.deleteHabitCommunity(communityId);
  }

}
