package com.example.backend.Transaction.mapper;

import com.example.backend.Transaction.vo.TransactionVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TransactionMapper {
  void insertTransaction(TransactionVO transaction);
  TransactionVO selectTransactionById(Long transactionId);
  List<TransactionVO> selectAllTransactions();
  void updateTransaction(TransactionVO transaction);
  void deleteTransaction(Long transactionId);
}
