package com.example.backend.Transaction.vo;

import lombok.Data;

@Data
public class TransactionVO {
  private Long transactionId;
  private Long accountId;
  private String transactionType;
  private int transactionAmount;
  private String createdAt;
  private String transactionInfo;
  private String categoryTitle;
  private Long totalSpent;
}
