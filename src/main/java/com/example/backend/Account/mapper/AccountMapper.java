package com.example.backend.Account.mapper;

import com.example.backend.Account.vo.AccountVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AccountMapper {
  void insertAccount(AccountVO account);
  AccountVO selectAccountById(Long accountId);
  List<AccountVO> selectAllAccounts();
  void updateAccount(AccountVO account);
  void deleteAccount(Long accountId);
}
