package com.example.backend.HabitCommunity.vo;

import lombok.Data;

@Data
public class HabitCommunityVO {
  private Long communityId;       // community_id -> communityId
  private Long habitId;           // habit_id -> habitId
  private int habitLikes;         // habit_likes -> habitLikes
  private int participants;
  private int complete;
  private String uploadDate;      // upload_date -> uploadDate
  private String habitTitle;      // habit_title -> habitTitle
  private String categoryTitle;   // category_title -> categoryTitle
  private String nickname;
}