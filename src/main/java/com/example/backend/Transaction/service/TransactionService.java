package com.example.backend.Transaction.service;

import com.example.backend.Transaction.mapper.TransactionMapper;
import com.example.backend.Transaction.vo.TransactionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

  private final TransactionMapper transactionMapper;

  @Autowired
  public TransactionService(TransactionMapper transactionMapper) {
    this.transactionMapper = transactionMapper;
  }

  // 거래 내역 삽입
  public void insertTransaction(TransactionVO transaction) {
    transactionMapper.insertTransaction(transaction);
  }

  // 특정 ID로 거래 내역 조회
  public TransactionVO selectTransactionById(Long transactionId) {
    return transactionMapper.selectTransactionById(transactionId);
  }

  // 모든 거래 내역 조회
  public List<TransactionVO> selectAllTransactions() {
    return transactionMapper.selectAllTransactions();
  }

  // 거래 내역 업데이트
  public void updateTransaction(TransactionVO transaction) {
    transactionMapper.updateTransaction(transaction);
  }

  // 거래 내역 삭제
  public void deleteTransaction(Long transactionId) {
    transactionMapper.deleteTransaction(transactionId);
  }
}
