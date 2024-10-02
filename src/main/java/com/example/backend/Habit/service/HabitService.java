package com.example.backend.Habit.service;

import com.example.backend.Habit.dto.HabitCheckCountDTO;
import com.example.backend.Habit.dto.HabitCheckRequestDTO;
import com.example.backend.Habit.vo.HabitCheckVO;
import com.example.backend.Habit.vo.MyHabitVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

public interface HabitService {
  // 1. 나의 습관 조회
  List<MyHabitVO> getMyHabit(@Param("userId") long userId);

  // 2. 습관 달성 체크
  void addHabitChecked(HabitCheckVO habitCheckVO);

  // 3. 달성한 습관 조회
  List<MyHabitVO> getCheckedHabit(long userId, String checkDate);

  // 4. 새로운 습관 작성
  void addMyHabit(MyHabitVO myHabitVO);               // step 1 : Habit 테이블에 습관 삽입
  Long getLastInsertedHabitId();                      // step 2 : 방금 추가한 habitId 조회
  void createHabitWithMyHabit(MyHabitVO myHabitVO);

  // 5. 습관 수정
  void modifyMyHabit(MyHabitVO myHabitVO);

  // 6. 습관 삭제
  void deleteMyHabit(long myHabitId);

  // 7. 습관 상태 변경
  void modifyMyHabitState(List<MyHabitVO> habitList);

  // 10. 오늘 절약 가능한 예상 금액
  int saveTotalAmount();

  // 11. 실제 절약 금액
  int saveRealAmount();

  // 12. 습관 커뮤니티에 업로드하기
  void addHabitCommunity();

  // 13. 인증 커뮤니티에 업로드하기
  void addPostCommunity();

  // 특정 습관에 해당하는 연속 달성일 정보 갖고오기
  List<HabitCheckCountDTO> getHabitCheckCounts(HabitCheckRequestDTO habitCheckRequestDTO);
}
