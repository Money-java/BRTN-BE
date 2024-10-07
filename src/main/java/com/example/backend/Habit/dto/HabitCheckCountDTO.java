package com.example.backend.Habit.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitCheckCountDTO {
    private int timeDiff;            // 오늘부터 습관 연연속일 날짜 차이
    private int continuousDateCount; // 연속된 날짜 개수
}
