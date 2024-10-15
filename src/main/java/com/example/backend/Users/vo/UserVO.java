package com.example.backend.Users.vo;

import lombok.Data;

@Data
public class UserVO {
  private Long userId;
  private String email;
  private String name;
  private String nickname;
  private String provider;
  private String role;
  private String avatar;
  private int reward;
}
