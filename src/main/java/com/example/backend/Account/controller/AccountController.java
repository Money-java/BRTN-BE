package com.example.backend.Account.controller;

import com.example.backend.Account.service.AccountService;
import com.example.backend.Account.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private final AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping
  public void insertAccount(@RequestBody AccountVO account) {
    accountService.insertAccount(account);
  }

  @GetMapping("/{id}")
  public AccountVO selectAccountById(@PathVariable Long id) {
    return accountService.selectAccountById(id);
  }

  @GetMapping
  public List<AccountVO> selectAllAccounts() {
    return accountService.selectAllAccounts();
  }

  @PutMapping
  public void updateAccount(@RequestBody AccountVO account) {
    accountService.updateAccount(account);
  }

  @DeleteMapping("/{id}")
  public void deleteAccount(@PathVariable Long id) {
    accountService.deleteAccount(id);
  }
}
