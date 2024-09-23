package com.example.backend.HabitCommunity.vo;

import lombok.Data;

@Data
public class HabitCommunityVO {
  private Long communityId;
  private Long habitId;
  private int habitLikes;
  private int participants;
  private int complete;
  private String uploadDate;
}
