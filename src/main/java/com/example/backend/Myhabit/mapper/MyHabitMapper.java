package com.example.backend.Myhabit.mapper;

import com.example.backend.Myhabit.vo.MyHabitVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MyHabitMapper {
  void insertMyHabit(MyHabitVO myHabit);
  MyHabitVO selectMyHabitById(Long myHabitId);
  List<MyHabitVO> selectAllMyHabits();
  void updateMyHabit(MyHabitVO myHabit);
  void deleteMyHabit(Long myHabitId);

  // 대기 상태인 습관 조회 (OFF)
  List<MyHabitVO> selectHabitsByStateOff();

  // 진행 상태인 습관 조회 (ON)
  List<MyHabitVO> selectHabitsByStateOn();

  // 습관 상태 ON으로 변경
  void updateHabitStateOn(Long myHabitId);

  // 습관 상태 OFF로 변경
  void updateHabitStateOff(Long myHabitId);

  // 상태가 'on'인 습관 제목을 가져오는 메서드
  List<String> selectHabitTitlesByState();
}
