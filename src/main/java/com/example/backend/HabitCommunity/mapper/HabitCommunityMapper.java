package com.example.backend.HabitCommunity.mapper;

import com.example.backend.HabitCommunity.vo.HabitCommunityVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface HabitCommunityMapper {
  void insertHabitCommunity(HabitCommunityVO habitCommunity);
  HabitCommunityVO selectHabitCommunityById(Long communityId);
  List<HabitCommunityVO> selectAllHabitCommunities();
  void updateHabitCommunity(HabitCommunityVO habitCommunity);
  void deleteHabitCommunity(Long communityId);

  // 최신순
  List<HabitCommunityVO> selectByUploadDate(String categoryName);

  // 좋아요순
  List<HabitCommunityVO> selectByLikes(String categoryName);

  // 참여자순
  List<HabitCommunityVO> selectByParticipants(String categoryName);
}
