package com.example.backend.Users.controller;

import com.example.backend.Users.service.UserService;
import com.example.backend.Users.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
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

  @PostMapping
  public void insertUser(@RequestBody UserVO user) {
    userService.insertUser(user);
  }

  @GetMapping("/{id}")
  public UserVO selectUserById(@PathVariable Long id) {
    return userService.selectUserById(id);
  }

  @GetMapping
  public List<UserVO> selectAllUsers() {
    return userService.selectAllUsers();
  }

  @PutMapping
  public void updateUser(@RequestBody UserVO user) {
    userService.updateUser(user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
  }
}
