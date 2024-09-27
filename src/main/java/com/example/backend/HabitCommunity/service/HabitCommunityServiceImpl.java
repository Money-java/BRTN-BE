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

  // HabitCommunity 삽입
  public void insertHabitCommunity(HabitCommunityVO habitCommunity) {
    habitCommunityMapper.insertHabitCommunity(habitCommunity);
  }

  // 특정 ID로 HabitCommunity 조회
  public HabitCommunityVO selectHabitCommunityById(Long communityId) {
    return habitCommunityMapper.selectHabitCommunityById(communityId);
  }

  // 모든 HabitCommunity 조회
  public List<HabitCommunityVO> selectAllHabitCommunities() {
    return habitCommunityMapper.selectAllHabitCommunities();
  }

  // HabitCommunity 업데이트
  public void updateHabitCommunity(HabitCommunityVO habitCommunity) {
    habitCommunityMapper.updateHabitCommunity(habitCommunity);
  }

  // HabitCommunity 삭제
  public void deleteHabitCommunity(Long communityId) {
    habitCommunityMapper.deleteHabitCommunity(communityId);
  }

  // 카테고리별 최신순 조회
  public List<HabitCommunityVO> selectByUploadDate(String categoryName) {
    return habitCommunityMapper.selectByUploadDate(categoryName);
  }

  // 카테고리별 좋아요순 조회
  public List<HabitCommunityVO> selectByLikes(String categoryName) {
    return habitCommunityMapper.selectByLikes(categoryName);
  }

  // 카테고리별 참여자순 조회
  public List<HabitCommunityVO> selectByParticipants(String categoryName) {
    return habitCommunityMapper.selectByParticipants(categoryName);
  }
}
