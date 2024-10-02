package com.example.backend.Users.service;

import com.example.backend.Users.mapper.UserMapper;
import com.example.backend.Users.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

  private final UserMapper userMapper;

  @Autowired
  public UsersServiceImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  // 신규 사용자 등록
  @Override
  public void insertUser(UserVO userVO) {
    userMapper.insertUser(userVO);
  }

  // 주어진 이메일을 사용해 기존 사용자 존재 여부 확인
  @Override
  public UserVO findUserByEmail(String email) {
    return userMapper.findUserByEmail(email);
  }

  // 특정 사용자 찾기
  @Override
  public UserVO findOneUser(String email) {
    return userMapper.findOneUser(email);
  }

  // 모든 사용자 찾기
  @Override
  public List<UserVO> findAllUsers() {
    return userMapper.findAllUsers();
  }

  // 닉네임 변경
  @Override
  public void updateUser(String nickname, Long userId) {
    userMapper.updateUser(nickname, userId);
  }

  // 회원 탈퇴
  @Override
  public void deleteUser(String email) {
    userMapper.deleteUser(email);
  }

  // 1. 리워드 증가
  // 인증 커뮤니티에서 인증 시 Users 테이블의 특정 사용자의 reward 올리기
  @Override
  public void incrementUserReward(Long userId) {
    userMapper.incrementUserReward(userId);
  }

  @Override
  public void registerUser(UserVO newUser) {

  }
}
