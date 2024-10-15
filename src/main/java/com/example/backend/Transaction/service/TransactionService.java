package com.example.backend.Transaction.service;

import com.example.backend.Transaction.vo.TransactionVO;

import java.util.Date;
import java.util.List;

public interface TransactionService {

    // 오늘 전체 지출 금액 조회
    Long getTotalSpentToday();
    // 오늘 전체 지출 내역 (카테고리 별)
    List<TransactionVO> getExpensesByCategoryToday();
    // 오늘 지출 TOP 5 (카테고리 별)
    List<TransactionVO> getExpensesTop();
    // 오늘 시간 별 지출 (3시간 단위)
    List<TransactionVO> getExpensesTime();
    // 선택한 습관 카테고리에 해당하는 지출 금액
    List<TransactionVO> getTotalSpentByHabitCategory(Long userId);

    // 선택한 습관 카테고리에 해당하는 지출 내역
    List<TransactionVO> getExpensesByHabitCategory(Long userId, String categoryTitle);
    // 과거 전체 지출 금액 (특정 날짜)
    Long getPastSpentDate(Date createdAt);

    // 과거 전체 지출 금액 (1일 전)
    Long getPastSpentDay();

    // 과거 전체 지출 금액 (1주 전)
    Long getPastSpentWeek();

    // 과거 전체 지출 금액 (1달 전)
    Long getPastSpentMonth();

    // 과거 전체 지출 금액 (1년 전)
    Long getPastSpentYear();

    // 과거 지출 TOP 5 (특정 날짜)
    List<TransactionVO> getTop5Date(Date createdAt);
    // 과거 지출 TOP 5 (1일 전)
    List<TransactionVO> getTop5Day();

    // 과거 지출 TOP 5 (1주 전)
    List<TransactionVO> getTop5Week();

    // 과거 지출 TOP 5 (1달 전)
    List<TransactionVO> getTop5Month();

    // 과거 지출 TOP 5 (1년 전)
    List<TransactionVO> getTop5Year();

    // 과거 시간 별 지출 (특정 날짜)
    List<TransactionVO> getTimeDate(Date createdAt);

    // 과거 시간 별 지출 (1일 전)
    List<TransactionVO> getTimeDay();

    // 과거 시간 별 지출 (1주 전)
    List<TransactionVO> getTimeWeek();

    // 과거 시간 별 지출 (1달 전)
    List<TransactionVO> getTimeMonth();
    // 과거 시간 별 지출 (1년 전)
    List<TransactionVO> getTimeYear();
}
