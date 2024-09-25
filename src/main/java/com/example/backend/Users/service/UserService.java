package com.example.backend.Users.service;

import com.example.backend.Users.mapper.UserMapper;
import com.example.backend.Users.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  private final UserMapper userMapper;

  @Autowired
  public UserService(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  // 사용자 등록
  public void insertUser(UserVO user) {
    userMapper.insertUser(user);
  }

  // 특정 ID로 사용자 조회
  public UserVO selectUserById(Long userId) {
    return userMapper.selectUserById(userId);
  }

  // 모든 사용자 조회
  public List<UserVO> selectAllUsers() {
    return userMapper.selectAllUsers();
  }

  // 사용자 정보 업데이트
  public void updateUser(UserVO user) {
    userMapper.updateUser(user);
  }

  // 사용자 삭제
  public void deleteUser(Long userId) {
    userMapper.deleteUser(userId);
  }
}
