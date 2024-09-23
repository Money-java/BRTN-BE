package com.example.backend.Habit.vo;

import lombok.Data;

@Data
public class HabitVO {
  private Long habitId;
  private Long userId;
  private String habitTitle;
  private String categoryTitle;
}
