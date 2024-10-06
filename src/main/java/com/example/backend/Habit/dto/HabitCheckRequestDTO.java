package com.example.backend.Habit.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HabitCheckRequestDTO {
    private Long userId;    // 사용자 ID
    private Long habitId;   // 습관 ID
}
