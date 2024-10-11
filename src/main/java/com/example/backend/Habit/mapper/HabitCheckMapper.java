package com.example.backend.Habit.mapper;

import com.example.backend.Habit.dto.HabitCheckCountDTO;
import com.example.backend.Habit.dto.HabitCheckRequestDTO;
import com.example.backend.Habit.vo.HabitCheckVO;
import com.example.backend.Habit.vo.MyHabitVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface HabitCheckMapper {

    // 2. 습관 달성 체크
    // 11. 인증 커뮤니티 업로드
    void addHabitChecked(HabitCheckVO habitCheckVO);     // step 3 : 인증 완료 후 HabitCheck 테이블에 삽입

    // 3. 달성한 습관 조회
    List<MyHabitVO> getCheckedHabit(Map<String, Object> map);

    // 얼마나 연속해서 습관을 달성했는지 조회
    List<HabitCheckCountDTO> getHabitCheckCounts(HabitCheckRequestDTO habitCheckRequestDTO);

    // 인증한 습관 개수 조회
    int countCheckedHabit(Long userId);

    // 인증한 모든 습관 금액 조회
    int countCheckedMoneyAll(Long userId);
  
    // 인증한 습관 개수 -- 날짜 별로 조회
    List<Map<String, Object>> countCheckedByDateRange(HashMap<String, Object> params);

    // 인증한 습관 금액 조회
    int countCheckedMoney(Long userId);

    // 인증한 습관 내역 조회
    List<MyHabitVO> getCheckedHabitAll(Long userId);

    // 인증한 습관 금액 조회
    int countCheckedMoneyByDate(Map<String, Object> map);
}
