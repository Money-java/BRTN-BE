package com.example.backend.Account.vo;

import lombok.Data;

@Data
public class AccountVO {
  private Long accountId;
  private Long userId;
  private String accountNum;
  private String accountName;
}
