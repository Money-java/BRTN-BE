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
}
