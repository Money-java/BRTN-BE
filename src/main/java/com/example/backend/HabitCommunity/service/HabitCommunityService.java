package com.example.backend.HabitCommunity.service;


import com.example.backend.HabitCommunity.vo.HabitCommunityVO;

import java.util.List;

public interface HabitCommunityService {
    // HabitCommunity 삽입
    void insertHabitCommunity(HabitCommunityVO habitCommunity0);
    // 특정 ID로 HabitCommunity 조회
    HabitCommunityVO selectHabitCommunityById(Long communityId);
    // 모든 HabitCommunity 조회
    List<HabitCommunityVO> selectAllHabitCommunities();
    // HabitCommunity 업데이트
    void updateHabitCommunity(HabitCommunityVO habitCommunity);
    // HabitCommunity 삭제
    void deleteHabitCommunity(Long communityId);
    // 카테고리별 최신순 조회
    List<HabitCommunityVO> selectByUploadDate(String categoryName);
    // 카테고리별 좋아요순 조회
    List<HabitCommunityVO> selectByLikes(String categoryName);
    // 카테고리별 참여자순 조회
    List<HabitCommunityVO> selectByParticipants(String categoryName);

}
