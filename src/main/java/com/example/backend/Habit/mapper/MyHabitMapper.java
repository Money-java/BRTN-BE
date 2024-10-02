package com.example.backend.Habit.mapper;

import com.example.backend.Habit.vo.HabitCheckVO;
import com.example.backend.Habit.vo.MyHabitVO;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
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
  MyHabitVO getHabitById(Long habitId);
  int getHabitCommunityById(Long habitId);
  void updateMyHabit(MyHabitVO myHabitVO);

  // 6. 습관 삭제
  void deleteMyHabit(MyHabitVO myHabitVO);

  // 7. 습관 상태 변경
  void updateMyHabitState(@Param("habitList") List<MyHabitVO> habitList, @Param("userId") Long userId);

  // 8. 절약 예상 금액
  int totalSaveAmount();

  // 9. 실제 절약 금액
  Integer realSaveAmount(long userId);

  // 10. 습관 커뮤니티 업로드
  void insertHabitCommunity(long habitId);

  // 11. 인증 커뮤니티 업로드
  int insertPostCommunity(PostCommunityVO postCommunityVO);              // step 1 : PostCommunity 테이블에 정보 삽입
  int checkHabitExists(PostCommunityVO postCommunityVO);                 // step 2 : HabitCheck 테이블의 정보 조회
  void addHabitCheck(PostCommunityVO postCommunityVO);                   // step 3 : HabitCheck 테이블에 입력하려는 정보가 없다면 삽입
  void updateMyHabitStateClear(PostCommunityVO postCommunityVO);         // step 4 : MyHabit 테이블에서 해당 습관 상태 완료로 변경
}
