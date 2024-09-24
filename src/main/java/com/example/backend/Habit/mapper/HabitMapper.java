package com.example.backend.Habit.mapper;

import com.example.backend.Habit.vo.HabitVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

public interface HabitMapper {
  void insertHabit(HabitVO habit);
  HabitVO selectHabitById(Long habitId);
  List<HabitVO> selectAllHabits();
  void updateHabit(HabitVO habit);
  void deleteHabit(Long habitId);
}
