package com.example.backend.Habit.service;

import com.example.backend.Habit.mapper.MyHabitMapper;
import com.example.backend.Habit.mapper.HabitCheckMapper;
import com.example.backend.Habit.vo.HabitCheckVO;
import com.example.backend.Habit.vo.MyHabitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class HabitService {

  @Autowired
  private MyHabitMapper myHabitMapper;
  private HabitCheckMapper habitCheckMapper;

  // 1. 나의 습관 조회
  public List<MyHabitVO> getMyHabit(long userId) {
    return myHabitMapper.selectMyHabit(userId);
  }

  // 2. 습관 달성 체크
  public void addHabitChecked(HabitCheckVO habitCheckVO) {
    habitCheckMapper.insertHabitChecked(habitCheckVO);
  }

  // 3. 달성한 습관 조회
  public List<MyHabitVO> getCheckedHabit() {
    return habitCheckMapper.selectHabitChecked();
  }

  // 4. 새로운 습관 체크 작성
  public void addMyHabit(MyHabitVO myHabitVO) {
    myHabitMapper.insertMyHabit(myHabitVO);
  }

  // 5. 체크된 습관 가져오기
  public void addMyHabitFromOther(MyHabitVO myHabitVO) {
    myHabitMapper.insertMyHabitFromOther(myHabitVO);
  }

  // 6. 습관 수정
  public void modifyMyHabit(MyHabitVO myHabitVO) {
    myHabitMapper.updateMyHabit(myHabitVO);
  }

  // 7. 습관 삭제
  public void deleteMyHabit(long myHabitId) {
    myHabitMapper.deleteMyHabit(myHabitId);
  }

  // 8. 습관 상태를 '진행'으로 변경
  public void modifyMyHabitStateS(long myHabitId) {
    myHabitMapper.updateMyHabitStateS(myHabitId);
  }

  // 9. 습관 상태를 '대기'로 변경
  public void modifyMyHabitStateW(long myHabitId) {
    myHabitMapper.updateMyHabitStateW(myHabitId);
  }
}
