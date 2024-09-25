package com.example.backend.Myhabit.service;

import com.example.backend.Myhabit.mapper.MyHabitMapper;
import com.example.backend.Myhabit.vo.MyHabitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyHabitService {

  private final MyHabitMapper myHabitMapper;

  @Autowired
  public MyHabitService(MyHabitMapper myHabitMapper) {
    this.myHabitMapper = myHabitMapper;
  }

  // MyHabit 삽입
  public void insertMyHabit(MyHabitVO myHabitVO) {
    myHabitMapper.insertMyHabit(myHabitVO);
  }

  // 특정 ID의 MyHabit 조회
  public MyHabitVO selectMyHabitById(Long myHabitId) {
    return myHabitMapper.selectMyHabitById(myHabitId);
  }

  // 모든 MyHabit 조회
  public List<MyHabitVO> selectAllMyHabits() {
    return myHabitMapper.selectAllMyHabits();
  }

  // MyHabit 업데이트
  public void updateMyHabit(MyHabitVO myHabitVO) {
    myHabitMapper.updateMyHabit(myHabitVO);
  }

  // MyHabit 삭제
  public void deleteMyHabit(Long myHabitId) {
    myHabitMapper.deleteMyHabit(myHabitId);
  }

  // 대기 상태인 습관 목록 조회 (OFF)
  public List<MyHabitVO> selectHabitsByStateOff() {
    return myHabitMapper.selectHabitsByStateOff();
  }

  // 진행 상태인 습관 목록 조회 (ON)
  public List<MyHabitVO> selectHabitsByStateOn() {
    return myHabitMapper.selectHabitsByStateOn();
  }

  // 습관 상태를 ON으로 변경
  public void updateHabitStateOn(Long myHabitId) {
    myHabitMapper.updateHabitStateOn(myHabitId);
  }

  // 습관 상태를 OFF로 변경
  public void updateHabitStateOff(Long myHabitId) {
    myHabitMapper.updateHabitStateOff(myHabitId);
  }

  // 상태가 ON인 습관의 제목 목록 가져오기
  public List<String> selectHabitTitlesByState() {
    return myHabitMapper.selectHabitTitlesByState();
  }
}
