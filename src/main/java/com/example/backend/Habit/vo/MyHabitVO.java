package com.example.backend.Habit.vo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyHabitVO {
    private Long myHabitId;           // 나의 습관 ID autoincrement
    private Long habitId;             // 습관 ID
    private Long userId;              // 사용자 ID
    private String habitTitle;        // 습관 제목
    private String categoryTitle;     // 카테고리 제목
    private String state;             // 습관 상태
    private Date checkDate;           // 체크 날짜
    private Long saveAmount;
    private String certification;
}
