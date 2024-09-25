package com.example.backend.HabitCheck.service;

import com.example.backend.HabitCheck.mapper.HabitCheckMapper;
import com.example.backend.HabitCheck.vo.HabitCheckVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HabitCheckService {

  private final HabitCheckMapper habitCheckMapper;

  @Autowired
  public HabitCheckService(HabitCheckMapper habitCheckMapper) {
    this.habitCheckMapper = habitCheckMapper;
  }

  // 달성한 습관 삽입
  public void insertHabitCheck(HabitCheckVO habitCheckVO) {
    habitCheckMapper.insertHabitCheck(habitCheckVO);
  }

  // 오늘 달성한 습관 목록
  public List<HabitCheckVO> getTodayHabitCheck(Long userId) {
    return habitCheckMapper.selectTodayHabitCheck(userId);
  }

  // 과거 달성한 습관 목록 (특정 날짜)
  public List<HabitCheckVO> getPastHabitCheckByDate(Map<String, Object> params) {
    return habitCheckMapper.selectPastHabitCheckDate(params);
  }

  // 과거 달성한 습관 목록 (1일 전)
  public List<HabitCheckVO> getPastHabitCheckByDay(Map<String, Object> params) {
    return habitCheckMapper.selectPastHabitCheckDay(params);
  }

  // 과거 달성한 습관 목록 (1주 전)
  public List<HabitCheckVO> getPastHabitCheckByWeek(Map<String, Object> params) {
    return habitCheckMapper.selectPastHabitCheckWeek(params);
  }

  // 과거 달성한 습관 목록 (1달 전)
  public List<HabitCheckVO> getPastHabitCheckByMonth(Map<String, Object> params) {
    return habitCheckMapper.selectPastHabitCheckMonth(params);
  }

  // 과거 달성한 습관 목록 (1년 전)
  public List<HabitCheckVO> getPastHabitCheckByYear(Map<String, Object> params) {
    return habitCheckMapper.selectPastHabitCheckYear(params);
  }

  // 특정 습관 조회
  public HabitCheckVO getHabitCheckById(Long myHabitId) {
    return habitCheckMapper.selectHabitCheckById(myHabitId);
  }

  // 모든 습관 조회
  public List<HabitCheckVO> getAllHabitChecks() {
    return habitCheckMapper.selectAllHabitChecks();
  }
}
