package com.example.backend.Habit.mapper;

import com.example.backend.Habit.vo.HabitCheckVO;
import com.example.backend.Habit.vo.MyHabitVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyHabitMapper {
  // 1. 나의 습관 조회
  List<MyHabitVO> getMyHabit(@Param("userId") long userId);

  // 4. 새로운 습관 작성
  void insertHabit(MyHabitVO myHabitVO);     // step 1 : Habit 테이블에 습관 삽입
  Long selectLastInsertedHabitId();          // step 2 : 방금 추가한 habitId 조회
  void insertMyHabit(MyHabitVO myHabitVO);   // step 3

  // 5. 습관 수정
  void updateMyHabit(MyHabitVO myHabitVO);

  // 6. 습관 삭제
  void deleteMyHabit(long myHabitId);

  // 7. 습관 상태 변경
  void updateMyHabitState(List<MyHabitVO> habitList);

  // 10. 오늘 절약 가능한 예상 금액
  int totalSaveAmount();

  // 11. 실제 절약 금액
  int realSaveAmount();

  // 12. 습관 커뮤니티에 업로드하기
  void insertHabitCommunity();

  // 13. 인증 커뮤니티에 업로드하기
  void insertPostCommunity();
}
