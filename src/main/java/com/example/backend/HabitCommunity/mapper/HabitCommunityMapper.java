package com.example.backend.HabitCommunity.mapper;

import com.example.backend.HabitCommunity.vo.HabitCommunityVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

public interface HabitCommunityMapper {
  void insertHabitCommunity(HabitCommunityVO habitCommunity);
  HabitCommunityVO selectHabitCommunityById(Long communityId);
  List<HabitCommunityVO> selectAllHabitCommunities();
  void updateHabitCommunity(HabitCommunityVO habitCommunity);
  void deleteHabitCommunity(Long communityId);
}
