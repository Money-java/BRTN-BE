package com.example.backend.HabitCommunity.mapper;

import com.example.backend.HabitCommunity.vo.HabitCommunityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface HabitCommunityMapper {

  // 1. 습관리스트 조회1 (동적 정렬: 최신순, 좋아요순, 참여자순, 달성자순)
  List<HabitCommunityVO> selectHabitList(@Param("categoryName") String categoryName,
                                         @Param("sortType") String sortType);

  // 2. 습관리스트 조회2
  // 내가 좋아요한 루틴 조회
  List<HabitCommunityVO> selectLikedCommunities(@Param("userId") String userId);

  // communityId로 HabitCommunity 조회
  HabitCommunityVO selectHabitCommunityById(Long communityId);

  // 모든 HabitCommunity 조회
  List<HabitCommunityVO> selectAllHabitCommunities();

  // HabitCommunity 수정
  void updateHabitCommunity(HabitCommunityVO habitCommunity);

  // HabitCommunity 삭제
  void deleteHabitCommunity(Long communityId);


}
