package com.example.backend.HabitCheck.service;

import com.example.backend.HabitCheck.mapper.HabitCheckMapper;
import com.example.backend.HabitCheck.vo.HabitCheckVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitCheckService {

  private final HabitCheckMapper habitCheckMapper;

  @Autowired
  public HabitCheckService(HabitCheckMapper habitCheckMapper) {
    this.habitCheckMapper = habitCheckMapper;
  }

  // HabitCheck 삽입
  public void insertHabitCheck(HabitCheckVO habitCheck) {
    habitCheckMapper.insertHabitCheck(habitCheck);
  }

  // 특정 ID로 HabitCheck 조회
  public HabitCheckVO selectHabitCheckById(Long myHabitId) {
    return habitCheckMapper.selectHabitCheckById(myHabitId);
  }

  // 모든 HabitCheck 조회
  public List<HabitCheckVO> selectAllHabitChecks() {
    return habitCheckMapper.selectAllHabitChecks();
  }

  // HabitCheck 업데이트
  public void updateHabitCheck(HabitCheckVO habitCheck) {
    habitCheckMapper.updateHabitCheck(habitCheck);
  }

  // HabitCheck 삭제
  public void deleteHabitCheck(Long myHabitId) {
    habitCheckMapper.deleteHabitCheck(myHabitId);
  }
}
