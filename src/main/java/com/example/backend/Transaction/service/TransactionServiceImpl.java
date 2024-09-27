package com.example.backend.Transaction.service;

import com.example.backend.Transaction.mapper.TransactionMapper;
import com.example.backend.Transaction.vo.TransactionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

  private final TransactionMapper transactionMapper;

  @Autowired
  public TransactionServiceImpl(TransactionMapper transactionMapper) {
    this.transactionMapper = transactionMapper;
  }

  // 오늘 전체 지출 금액 조회
  public Long getTotalSpentToday() {
    return transactionMapper.selectTotalSpentToday();
  }

  // 오늘 전체 지출 내역 (카테고리 별)
  public List<TransactionVO> getExpensesByCategoryToday() {
    return transactionMapper.selectExpensesByCategoryToday();
  }

  // 오늘 지출 TOP 5 (카테고리 별)
  public List<TransactionVO> getExpensesTop() {
    return transactionMapper.selectExpensesTop();
  }

  // 오늘 시간 별 지출 (3시간 단위)
  public List<TransactionVO> getExpensesTime() {
    return transactionMapper.selectExpensesTime();
  }

  // 선택한 습관 카테고리에 해당하는 지출 금액
  public List<TransactionVO> getTotalSpentByHabitCategory(Long userId) {
    return transactionMapper.selectTotalSpentByHabitCategory(userId);
  }

  // 선택한 습관 카테고리에 해당하는 지출 내역
  public List<TransactionVO> getExpensesByHabitCategory(Long userId, String categoryTitle) {
    return transactionMapper.selectExpensesByHabitCategory(userId, categoryTitle);
  }

  // 과거 전체 지출 금액 (특정 날짜)
  public Long getPastSpentDate(Date createdAt) {
    return transactionMapper.selectPastSpentDate(createdAt);
  }

  // 과거 전체 지출 금액 (1일 전)
  public Long getPastSpentDay() {
    return transactionMapper.selectPastSpentDay();
  }

  // 과거 전체 지출 금액 (1주 전)
  public Long getPastSpentWeek() {
    return transactionMapper.selectPastSpentWeek();
  }

  // 과거 전체 지출 금액 (1달 전)
  public Long getPastSpentMonth() {
    return transactionMapper.selectPastSpentMonth();
  }

  // 과거 전체 지출 금액 (1년 전)
  public Long getPastSpentYear() {
    return transactionMapper.selectPastSpentYear();
  }

  // 과거 지출 TOP 5 (특정 날짜)
  public List<TransactionVO> getTop5Date(Date createdAt) {
    return transactionMapper.selectTop5Date(createdAt);
  }

  // 과거 지출 TOP 5 (1일 전)
  public List<TransactionVO> getTop5Day() {
    return transactionMapper.selectTop5Day();
  }

  // 과거 지출 TOP 5 (1주 전)
  public List<TransactionVO> getTop5Week() {
    return transactionMapper.selectTop5Week();
  }

  // 과거 지출 TOP 5 (1달 전)
  public List<TransactionVO> getTop5Month() {
    return transactionMapper.selectTop5Month();
  }

  // 과거 지출 TOP 5 (1년 전)
  public List<TransactionVO> getTop5Year() {
    return transactionMapper.selectTop5Year();
  }

  // 과거 시간 별 지출 (특정 날짜)
  public List<TransactionVO> getTimeDate(Date createdAt) {
    return transactionMapper.selectTimeDate(createdAt);
  }

  // 과거 시간 별 지출 (1일 전)
  public List<TransactionVO> getTimeDay() {
    return transactionMapper.selectTimeDay();
  }

  // 과거 시간 별 지출 (1주 전)
  public List<TransactionVO> getTimeWeek() {
    return transactionMapper.selectTimeWeek();
  }

  // 과거 시간 별 지출 (1달 전)
  public List<TransactionVO> getTimeMonth() {
    return transactionMapper.selectTimeMonth();
  }

  // 과거 시간 별 지출 (1년 전)
  public List<TransactionVO> getTimeYear() {
    return transactionMapper.selectTimeYear();
  }
}
