package com.example.backend.Users.controller;

import com.example.backend.Users.service.UsersService;
import com.example.backend.Users.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

  private final UsersService usersService;

  @Autowired
  public UsersController(UsersService usersService) {
    this.usersService = usersService;
  }

  // 신규 사용자 등록
  @PostMapping("/register")
  public void insertUser(@RequestBody UserVO userVO) {
    usersService.insertUser(userVO);
  }

  // 주어진 이메일을 사용해 기존 사용자 존재 여부 확인
//  @GetMapping("/login")
//  public UserVO findUserByEmail(@RequestParam String email) {
//    return usersService.findUserByEmail(email);
//  }

  // 특정 사용자 찾기
  @GetMapping("/find")
  public UserVO findOneUser(@RequestParam String email) {
    return usersService.findOneUser(email);
  }

  // 모든 사용자 찾기
  @GetMapping("/all")
  public List<UserVO> findAllUsers() {
    return usersService.findAllUsers();
  }

  // 닉네임 변경
  @PutMapping("/updateNickname")
  public void updateUser(@RequestParam String nickname, @RequestParam Long userId) {
    usersService.updateUser(nickname, userId);
  }

  // 회원 탈퇴
  @DeleteMapping("/delete")
  public void deleteUser(@RequestParam String email) {
    usersService.deleteUser(email);
  }

  // 1. 리워드 증가
  // 인증 커뮤니티에서 인증 시 Users 테이블의 특정 사용자의 reward 올리기
  @PostMapping("/increment-reward")
  public void incrementUserReward(@RequestParam Long userId) {
    usersService.incrementUserReward(userId);
  }
}
