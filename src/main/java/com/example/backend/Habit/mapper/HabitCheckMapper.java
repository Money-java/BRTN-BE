package com.example.backend.Habit.mapper;

import com.example.backend.Habit.vo.HabitCheckVO;
import com.example.backend.Habit.vo.MyHabitVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface HabitCheckMapper {

    // 2. 습관 달성 체크
    void addHabitChecked(HabitCheckVO habitCheckVO);

    // 3. 달성한 습관 조회
    List<MyHabitVO> getCheckedHabit(Map<String, Object> map);
}
