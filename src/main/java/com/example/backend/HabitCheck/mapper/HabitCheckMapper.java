package com.example.backend.HabitCheck.mapper;

import com.example.backend.HabitCheck.vo.HabitCheckVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface HabitCheckMapper {
  void insertHabitCheck(HabitCheckVO habitCheck);
  HabitCheckVO selectHabitCheckById(Long myHabitId);
  List<HabitCheckVO> selectAllHabitChecks();
  void updateHabitCheck(HabitCheckVO habitCheck);
  void deleteHabitCheck(Long myHabitId);
}
