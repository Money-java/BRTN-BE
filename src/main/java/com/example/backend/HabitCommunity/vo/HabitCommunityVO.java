package com.example.backend.HabitCommunity.vo;

import lombok.Data;

@Data
public class HabitCommunityVO {
  private Long communityId;
  private Long habitId;
  private int habit_likes;
  private int participants;
  private int complete;
  private String uploadDate;
  private String habit_title;       // 조인된 Habit 테이블에서 가져오는 습관 제목
  private String category_title;    // 조인된 Habit 테이블에서 가져오는 카테고리 명
  private String nickname;         // 조인된 users 테이블에서 가져오는 닉네임
}
