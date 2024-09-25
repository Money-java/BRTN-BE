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

  // 신규 사용자 등록
  public void registerUser(UserVO user) {
    userMapper.insertUser(user);
  }

  // 이메일로 사용자 찾기
  public UserVO findUserByEmail(String email) {
    return userMapper.findUserByEmail(email);
  }

  // 특정 사용자 찾기
  public UserVO findOneUser(String email) {
    return userMapper.findOneUser(email);
  }

  // 모든 사용자 찾기
  public List<UserVO> findAllUsers() {
    return userMapper.findAllUsers();
  }

  // 닉네임 변경
  public void updateUser(String userId, String newNickname) {
    userMapper.updateUser(newNickname, userId);
  }

  // 사용자 삭제
  public void deleteUser(String email) {
    userMapper.deleteUser(email);
  }
}
