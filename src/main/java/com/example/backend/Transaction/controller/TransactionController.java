package com.example.backend.Transaction.controller;

import com.example.backend.Transaction.service.TransactionService;
import com.example.backend.Transaction.vo.TransactionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  @Autowired
  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping
  public void insertTransaction(@RequestBody TransactionVO transaction) {
    transactionService.insertTransaction(transaction);
  }

  @GetMapping("/{id}")
  public TransactionVO selectTransactionById(@PathVariable Long id) {
    return transactionService.selectTransactionById(id);
  }

  @GetMapping
  public List<TransactionVO> selectAllTransactions() {
    return transactionService.selectAllTransactions();
  }

  @PutMapping
  public void updateTransaction(@RequestBody TransactionVO transaction) {
    transactionService.updateTransaction(transaction);
  }

  @DeleteMapping("/{id}")
  public void deleteTransaction(@PathVariable Long id) {
    transactionService.deleteTransaction(id);
  }
}
