package com.example.backend.HabitCheck.vo;

import lombok.Data;

@Data
public class HabitCheckVO {
  private String checkDate;
  private Long myHabitId;
  private Long userId;
  private Long habitId;
}
