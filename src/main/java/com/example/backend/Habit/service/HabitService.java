package com.example.backend.Habit.service;

import com.example.backend.Habit.vo.HabitCheckVO;
import com.example.backend.Habit.vo.MyHabitVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface HabitService {
  // 1. 나의 습관 조회
  List<MyHabitVO> getMyHabit(@Param("userId") long userId);

  // 2. 습관 달성 체크
  void addHabitChecked(HabitCheckVO habitCheckVO);

  // 3. 달성한 습관 조회
  List<MyHabitVO> getCheckedHabit();

  // 4. 새로운 습관 체크 작성
  void addMyHabit(MyHabitVO myHabitVO);

  // 5. 체크된 습관 가져오기
  void addMyHabitFromOther(MyHabitVO myHabitVO);

  // 6. 습관 수정
  void modifyMyHabit(MyHabitVO myHabitVO);

  // 7. 습관 삭제
  void deleteMyHabit(long myHabitId);

  // 8. 습관 상태를 '진행'으로 변경
  void modifyMyHabitStateS(long myHabitId);

  // 9. 습관 상태를 '대기'로 변경
  void modifyMyHabitStateW(long myHabitId);

  // 10. 오늘 절약 가능한 예상 금액
  int saveTotalAmount();

  // 11. 실제 절약 금액
  int saveRealAmount();

  // 12. 습관 커뮤니티에 업로드하기
  void addHabitCommunity();

  // 13. 인증 커뮤니티에 업로드하기
  void addPostCommunity();
}
