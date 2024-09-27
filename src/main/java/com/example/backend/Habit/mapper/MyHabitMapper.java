package com.example.backend.Habit.mapper;

import com.example.backend.Habit.vo.MyHabitVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MyHabitMapper {
  // 1. 나의 습관 조회
  List<MyHabitVO> selectMyHabit(long userId);

  // 4. 새로운 습관 체크 작성
  void insertMyHabit(MyHabitVO myHabitVO);

  // 5. 체크된 습관 가져오기
  void insertMyHabitFromOther(MyHabitVO myHabitVO);

  // 6. 습관 수정
  void updateMyHabit(MyHabitVO myHabitVO);

  // 7. 습관 삭제
  void deleteMyHabit(long myHabitId);

  // 8. 습관 상태를 '진행'으로 변경
  void updateMyHabitStateS(long myHabitId);

  // 9. 습관 상태를 '대기'로 변경
  void updateMyHabitStateW(long myHabitId);
}
