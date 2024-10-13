package com.example.backend.Habit.service;

import com.example.backend.Habit.dto.HabitCheckCountDTO;
import com.example.backend.Habit.dto.HabitCheckRequestDTO;
import com.example.backend.Habit.dto.HabitCreateResponseDTO;
import com.example.backend.Habit.dto.MyHabitInfoDTO;
import com.example.backend.Habit.vo.HabitCheckVO;
import com.example.backend.Habit.vo.MyHabitVO;
import com.example.backend.HabitCommunity.vo.HabitCommunityVO;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface HabitService {
  // 1. 나의 습관 조회
  List<MyHabitVO> getMyHabit(@Param("userId") long userId);

  MyHabitVO getHabitById(long habitId);

  List<MyHabitInfoDTO> getMyTodayHabitInfo(@Param("userId") long userId);

  // 2. 습관 달성 체크
  void addHabitChecked(HabitCheckVO habitCheckVO);

  // 3. 달성한 습관 조회
  List<MyHabitVO> getCheckedHabit(long userId, String checkDate);

  String checkDuplicateHabit(String habitTitle);

  // 4. 새로운 습관 작성
  void addMyHabit(MyHabitVO myHabitVO);               // step 1 : Habit 테이블에 습관 삽입
  Long getLastInsertedHabitId();                      // step 2 : 방금 추가한 habitId 조회
  HabitCreateResponseDTO createHabitWithMyHabit(MyHabitVO myHabitVO);

  // 5. 습관 수정
  String modifyMyHabit(MyHabitVO myHabitVO);

  // 6. 습관 삭제
  void deleteMyHabit(Long myHabitId);
  void deleteHabitParticipant(Long habitId);

  // 7. 습관 상태 변경
  void modifyMyHabitState(List<MyHabitVO> habitList, long userId);

  // 8. 절약 예상 금액
  int saveTotalAmount();

  // 9. 실제 절약 금액
  int saveRealAmount(long userId);

  // 10. 습관 커뮤니티 업로드
  String addHabitCommunity(long habitId);

  // 특정 습관에 해당하는 연속 달성일 정보 갖고오기
  List<HabitCheckCountDTO> getHabitCheckCounts(HabitCheckRequestDTO habitCheckRequestDTO);
  
  // 11. 인증 커뮤니티 업로드
  void addPostCommunity(PostCommunityVO postCommunityVO);

  // 인증한 습관 개수 조회
  int countCheckedHabit(long userId);

  // 인증한 습관 개수 -- 날짜 별 조회
  List<Map<String, Object>> countCheckedByDateRange(HashMap<String, Object> params);

  // 인증한 습관 금액 조회
  int countCheckedMoney(long userId);

  // 달성한 습관 조회
  List<MyHabitVO> getCheckedHabitAll(long userId);

  int countCheckedMoneByDate(long userId, String checkDate);
}
