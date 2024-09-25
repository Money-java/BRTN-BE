package com.example.backend.Users.controller;

import com.example.backend.Users.service.UserService;
import com.example.backend.Users.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  // 신규 사용자 등록
  @PostMapping
  public ResponseEntity<Void> registerUser(@RequestBody UserVO user) {
    userService.registerUser(user);
    return ResponseEntity.ok().build();
  }

  // 이메일로 사용자 찾기
  @GetMapping("/{email}")
  public ResponseEntity<UserVO> findUserByEmail(@PathVariable String email) {
    UserVO user = userService.findUserByEmail(email);
    return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
  }

  // 모든 사용자 찾기
  @GetMapping
  public ResponseEntity<List<UserVO>> findAllUsers() {
    List<UserVO> users = userService.findAllUsers();
    return ResponseEntity.ok(users);
  }

  // 닉네임 변경
  @PutMapping("/{userId}/nickname")
  public ResponseEntity<Void> updateUser(@PathVariable String userId, @RequestParam String newNickname) {
    userService.updateUser(userId, newNickname);
    return ResponseEntity.ok().build();
  }

  // 사용자 삭제
  @DeleteMapping("/{email}")
  public ResponseEntity<Void> deleteUser(@PathVariable String email) {
    userService.deleteUser(email);
    return ResponseEntity.ok().build();
  }
}
