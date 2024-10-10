package com.example.backend.Habit.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MyHabitInfoDTO {
    private Long myHabitId;
    private Long habitId;
    private Long writerId;
    private String habitTitle;
    private String categoryTitle;
    private String state;
//    private Date checkDate;
    private Long saveAmount;
    private String certification;
    private String isCheckedToday;
}
