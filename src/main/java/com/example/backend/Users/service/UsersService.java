package com.example.backend.Users.service;

import com.example.backend.Users.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersService {

  // 신규 사용자 등록
  void insertUser(UserVO userVO);

  // 주어진 이메일과 소셜 provider을 사용해 기존 사용자 존재 여부 확인
  UserVO findUserByEmailandProvider(String email, String provider);

  void updateUserProfile(Long userId, String nickname, String avatar);

  // 아이디로 특정 사용자 찾기
  UserVO findUserById(Long userId);

  // 특정 사용자 찾기
  UserVO findOneUser(String email);

  // 모든 사용자 찾기
  List<UserVO> findAllUsers();


  // 회원 탈퇴
  void deleteUser(String email);

  // 1. 리워드 증가
  // 인증 커뮤니티에서 인증 시 Users 테이블의 특정 사용자의 reward 올리기
  void incrementUserReward(Long userId);

  // 닉네임 업데이트 메서드
  void updateUser(String nickname, Long userId);
}
