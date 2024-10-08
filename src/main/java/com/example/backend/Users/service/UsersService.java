package com.example.backend.Users.service;

import com.example.backend.Users.vo.UserVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UsersService {

  // 신규 사용자 등록
  void insertUser(UserVO userVO);

  // 주어진 이메일과 소셜 provider을 사용해 기존 사용자 존재 여부 확인
  UserVO findUserByEmailandProvider(String email, String provider);

  void updateUserProfile(Long userId, String nickname, String avatar);

  UserVO findUserById(Long userId);

  void updateUserProfile(Long userId, String nickname, MultipartFile image);

  // 특정 사용자 찾기
  UserVO findOneUser(String email);

  // 모든 사용자 찾기
  List<UserVO> findAllUsers();


  // 회원 탈퇴
  void deleteUser(String email);

  // 1. 리워드 증가
  // 인증 커뮤니티에서 인증 시 Users 테이블의 특정 사용자의 reward 올리기
  void incrementUserReward(Long userId, int userReward);

  // 닉네임 업데이트 메서드
  void updateUser(String nickname, Long userId);

  // 프로필 변경 - 이미지 변경
  void updateUserProfileWithImage(Long userId, MultipartFile image);
}
