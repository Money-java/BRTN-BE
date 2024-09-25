package com.example.backend.Transaction.mapper;

import com.example.backend.Transaction.vo.TransactionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface TransactionMapper {
  // 오늘 전체 지출 금액 조회
  Long selectTotalSpentToday();

  // 오늘 전체 지출 내역 (카테고리 별) 조회
  List<TransactionVO> selectExpensesByCategoryToday();

  // 오늘 지출 TOP 5 (카테고리 별) 조회
  List<TransactionVO> selectExpensesTop();

  // 오늘 시간 별 지출 (3시간 단위) 조회
  List<TransactionVO> selectExpensesTime();

  // 선택한 습관 카테고리에 해당하는 지출 금액 조회
  List<TransactionVO> selectTotalSpentByHabitCategory(@Param("userId") Long userId);

  // 선택한 습관 카테고리에 해당하는 지출 내역 조회
  List<TransactionVO> selectExpensesByHabitCategory(@Param("userId") Long userId, @Param("categoryTitle") String categoryTitle);

  // 과거 전체 지출 금액 (특정 날짜) 조회
  Long selectPastSpentDate(@Param("createdAt") Date createdAt);

  // 과거 전체 지출 금액 (1일 전) 조회
  Long selectPastSpentDay();

  // 과거 전체 지출 금액 (1주 전) 조회
  Long selectPastSpentWeek();

  // 과거 전체 지출 금액 (1달 전) 조회
  Long selectPastSpentMonth();

  // 과거 전체 지출 금액 (1년 전) 조회
  Long selectPastSpentYear();

  // 오늘 지출 TOP 5 (특정 날짜) 조회
  List<TransactionVO> selectTop5Date(@Param("createdAt") Date createdAt);

  // 오늘 지출 TOP 5 (1일 전) 조회
  List<TransactionVO> selectTop5Day();

  // 오늘 지출 TOP 5 (1주 전) 조회
  List<TransactionVO> selectTop5Week();

  // 오늘 지출 TOP 5 (1달 전) 조회
  List<TransactionVO> selectTop5Month();

  // 오늘 지출 TOP 5 (1년 전) 조회
  List<TransactionVO> selectTop5Year();

  // 과거의 시간대 별 지출 금액 조회 (특정 날짜)
  List<TransactionVO> selectTimeDate(@Param("createdAt") Date createdAt);

}
