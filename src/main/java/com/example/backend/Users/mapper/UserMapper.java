package com.example.backend.Users.mapper;

import com.example.backend.Users.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
  // 신규 사용자 등록
  void insertUser(UserVO user);

  // 기존 사용자 로그인
  UserVO findUserByEmail(@Param("email") String email);

  // 특정 사용자 찾기
  UserVO findOneUser(@Param("email") String email);

  // 모든 사용자 찾기
  List<UserVO> findAllUsers();

  // 회원 탈퇴
  void deleteUser(@Param("email") String email);
}
