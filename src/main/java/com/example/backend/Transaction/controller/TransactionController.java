package com.example.backend.Transaction.controller;

import com.example.backend.Transaction.service.TransactionService;
import com.example.backend.Transaction.vo.TransactionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.sql.Date;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  @Autowired
  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  // 오늘 전체 지출 금액 조회
  @GetMapping("/total-spent-today")
  public Long getTotalSpentToday() {
    return transactionService.getTotalSpentToday();
  }

  // 오늘 전체 지출 내역 (카테고리 별)
  @GetMapping("/expenses-by-category-today")
  public List<TransactionVO> getExpensesByCategoryToday() {
    return transactionService.getExpensesByCategoryToday();
  }

  // 오늘 지출 TOP 5 (카테고리 별)
  @GetMapping("/expenses-top")
  public List<TransactionVO> getExpensesTop() {
    return transactionService.getExpensesTop();
  }

  // 오늘 시간 별 지출 (3시간 단위)
  @GetMapping("/expenses-time")
  public List<TransactionVO> getExpensesTime() {
    return transactionService.getExpensesTime();
  }

  // 선택한 습관 카테고리에 해당하는 지출 금액
  @GetMapping("/total-spent-by-habit-category")
  public List<TransactionVO> getTotalSpentByHabitCategory(@RequestParam Long userId) {
    return transactionService.getTotalSpentByHabitCategory(userId);
  }

  // 선택한 습관 카테고리에 해당하는 지출 내역
  @GetMapping("/expenses-by-habit-category")
  public List<TransactionVO> getExpensesByHabitCategory(@RequestParam Long userId, @RequestParam String categoryTitle) {
    return transactionService.getExpensesByHabitCategory(userId, categoryTitle);
  }

  // 과거 전체 지출 금액 (특정 날짜)
  @GetMapping("/past-spent-date")
  public Long getPastSpentDate(@RequestParam Date createdAt) {
    return transactionService.getPastSpentDate(createdAt);
  }

  // 과거 전체 지출 금액 (1일 전)
  @GetMapping("/past-spent-day")
  public Long getPastSpentDay() {
    return transactionService.getPastSpentDay();
  }

  // 과거 전체 지출 금액 (1주 전)
  @GetMapping("/past-spent-week")
  public Long getPastSpentWeek() {
    return transactionService.getPastSpentWeek();
  }

  // 과거 전체 지출 금액 (1달 전)
  @GetMapping("/past-spent-month")
  public Long getPastSpentMonth() {
    return transactionService.getPastSpentMonth();
  }

  // 과거 전체 지출 금액 (1년 전)
  @GetMapping("/past-spent-year")
  public Long getPastSpentYear() {
    return transactionService.getPastSpentYear();
  }

  // 과거 지출 TOP 5 (특정 날짜)
  @GetMapping("/top5-date")
  public List<TransactionVO> getTop5Date(@RequestParam Date createdAt) {
    return transactionService.getTop5Date(createdAt);
  }

  // 과거 지출 TOP 5 (1일 전)
  @GetMapping("/top5-day")
  public List<TransactionVO> getTop5Day() {
    return transactionService.getTop5Day();
  }

  // 과거 지출 TOP 5 (1주 전)
  @GetMapping("/top5-week")
  public List<TransactionVO> getTop5Week() {
    return transactionService.getTop5Week();
  }

  // 과거 지출 TOP 5 (1달 전)
  @GetMapping("/top5-month")
  public List<TransactionVO> getTop5Month() {
    return transactionService.getTop5Month();
  }

  // 과거 지출 TOP 5 (1년 전)
  @GetMapping("/top5-year")
  public List<TransactionVO> getTop5Year() {
    return transactionService.getTop5Year();
  }

  // 과거 시간 별 지출 (특정 날짜)
  @GetMapping("/time-date")
  public List<TransactionVO> getTimeDate(@RequestParam Date createdAt) {
    return transactionService.getTimeDate(createdAt);
  }

  // 과거 시간 별 지출 (1일 전)
  @GetMapping("/time-day")
  public List<TransactionVO> getTimeDay() {
    return transactionService.getTimeDay();
  }

  // 과거 시간 별 지출 (1주 전)
  @GetMapping("/time-week")
  public List<TransactionVO> getTimeWeek() {
    return transactionService.getTimeWeek();
  }

  // 과거 시간 별 지출 (1달 전)
  @GetMapping("/time-month")
  public List<TransactionVO> getTimeMonth() {
    return transactionService.getTimeMonth();
  }

  // 과거 시간 별 지출 (1년 전)
  @GetMapping("/time-year")
  public List<TransactionVO> getTimeYear() {
    return transactionService.getTimeYear();
  }
}
