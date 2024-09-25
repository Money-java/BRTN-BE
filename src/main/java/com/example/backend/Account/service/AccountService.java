package com.example.backend.Account.service;

import com.example.backend.Account.mapper.AccountMapper;
import com.example.backend.Account.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

  private final AccountMapper accountMapper;

  @Autowired
  public AccountService(AccountMapper accountMapper) {
    this.accountMapper = accountMapper;
  }

  // Account 삽입
  public void insertAccount(AccountVO account) {
    accountMapper.insertAccount(account);
  }

  // 특정 ID로 Account 조회
  public AccountVO selectAccountById(Long accountId) {
    return accountMapper.selectAccountById(accountId);
  }

  // 모든 Account 조회
  public List<AccountVO> selectAllAccounts() {
    return accountMapper.selectAllAccounts();
  }

  // Account 업데이트
  public void updateAccount(AccountVO account) {
    accountMapper.updateAccount(account);
  }

  // Account 삭제
  public void deleteAccount(Long accountId) {
    accountMapper.deleteAccount(accountId);
  }
}
