package com.example.backend.Habit.vo;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitCheckVO {
    private Long myHabitId;    // 사용자 습관 ID
    private Long habitId;      // 습관 ID
    private Long userId;       // 사용자 ID
    private Date checkDate;    // 체크 날짜
}
