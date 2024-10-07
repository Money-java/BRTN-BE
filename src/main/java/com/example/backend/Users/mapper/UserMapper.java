package com.example.backend.Users.mapper;

import com.example.backend.Users.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

  // 1. 리워드 증가
  // 인증 커뮤니티에서 인증 시 Users 테이블의 특정 사용자의 reward 올리기
  void incrementUserReward(@Param("userId") Long userId);

  // 아이디로 특정 사용자 찾기
  UserVO findUserById(@Param("userId") Long userId);

  // 신규 사용자 등록
  void insertUser(UserVO user);

  // 닉네임 변경
  void updateUserProfile(@Param("userId") Long userId, @Param("nickname") String nickname,@Param("avatar") String avatar);

  // 기존 사용자 로그인
  UserVO findUserByEmailandProvider(@Param("email") String email, @Param("provider") String provider);

  // 특정 사용자 찾기
  UserVO findOneUser(@Param("email") String email);

  // 모든 사용자 찾기
  List<UserVO> findAllUsers();

  // 회원 탈퇴
  void deleteUser(@Param("email") String email);

  // 닉네임 변경
  void updateUser(@Param("nickname") String nickname, @Param("userId") Long userId);

  // 닉네임 업데이트 메서드
  void updateUser(Map<String, Object> params);
}
