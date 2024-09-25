package com.example.backend.Myhabit.vo;

import lombok.Data;

@Data
public class MyHabitVO {
  private Long myHabitId;
  private Long userId;
  private Long habitId;
  private String state;
  private String categoryTitle;
}
