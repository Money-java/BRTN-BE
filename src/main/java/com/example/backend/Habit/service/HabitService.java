package com.example.backend.Habit.service;

import com.example.backend.Habit.mapper.HabitMapper;
import com.example.backend.Habit.vo.HabitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {

  private final HabitMapper habitMapper;

  @Autowired
  public HabitService(HabitMapper habitMapper) {
    this.habitMapper = habitMapper;
  }

  // Habit 삽입
  public void insertHabit(HabitVO habit) {
    habitMapper.insertHabit(habit);
  }

  // 특정 ID로 Habit 조회
  public HabitVO selectHabitById(Long habitId) {
    return habitMapper.selectHabitById(habitId);
  }

  // 모든 Habit 조회
  public List<HabitVO> selectAllHabits() {
    return habitMapper.selectAllHabits();
  }

  // Habit 업데이트
  public void updateHabit(HabitVO habit) {
    habitMapper.updateHabit(habit);
  }

  // Habit 삭제
  public void deleteHabit(Long habitId) {
    habitMapper.deleteHabit(habitId);
  }
}
