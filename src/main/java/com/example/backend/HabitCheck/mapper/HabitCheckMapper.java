package com.example.backend.HabitCheck.mapper;

import com.example.backend.HabitCheck.vo.HabitCheckVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface HabitCheckMapper {

  // 달성한 습관 삽입
  void insertHabitCheck(HabitCheckVO habitCheckVO);

  // 오늘 달성한 습관 목록
  List<HabitCheckVO> selectTodayHabitCheck(long userId);

  // 과거 달성한 습관 목록
  List<HabitCheckVO> selectPastHabitCheck(Map<String, Object> params);

  // 과거 달성한 습관 목록 (특정 날짜)
  List<HabitCheckVO> selectPastHabitCheckDate(Map<String, Object> params);

  // 과거 달성한 습관 목록 (1일 전)
  List<HabitCheckVO> selectPastHabitCheckDay(Map<String, Object> params);

  // 과거 달성한 습관 목록 (1주 전)
  List<HabitCheckVO> selectPastHabitCheckWeek(Map<String, Object> params);

  // 과거 달성한 습관 목록 (1달 전)
  List<HabitCheckVO> selectPastHabitCheckMonth(Map<String, Object> params);

  // 과거 달성한 습관 목록 (1년 전)
  List<HabitCheckVO> selectPastHabitCheckYear(Map<String, Object> params);

  // 특정 습관
  HabitCheckVO selectHabitCheckById(long myHabitId);

  // 모든 습관
  List<HabitCheckVO> selectAllHabitChecks();
}
