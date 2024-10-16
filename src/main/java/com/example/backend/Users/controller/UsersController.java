package com.example.backend.Users.controller;

import com.example.backend.Users.service.UsersService;
import com.example.backend.Users.service.UsersServiceImpl;
import com.example.backend.Users.vo.UserVO;
import com.example.backend.security.jwt.JWTUtil;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UsersController {

  private final UsersServiceImpl usersService;
  private final JWTUtil jwtUtil;

  @Autowired
  public UsersController(UsersServiceImpl usersService, JWTUtil jwtUtil) {
    this.usersService = usersService;
    this.jwtUtil = jwtUtil;
  }

  // 신규 사용자 등록
  @PostMapping("/register")
  public void insertUser(@RequestBody UserVO userVO) {
    usersService.insertUser(userVO);
  }

  @CrossOrigin(origins = "http://localhost:5173")
  @PostMapping("/findId")
  public ResponseEntity<?> findId(HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      return ResponseEntity.status(401).body("JWT Token is missing or invalid.");
    }
    String token = authorizationHeader.substring(7);

    String email = jwtUtil.getUserEmail(token); //토큰에서 email 추출
    String provider = jwtUtil.getUserProvider(token);// 토큰에서 provider 추출
    UserVO user = usersService.findUserByEmailandProvider(email,provider);
    if(user == null) {
      return ResponseEntity.status(401).body("해당 사용자가 존재하지 않습니다.");
    }
    return ResponseEntity.ok(user);
  }

  @PostMapping("/updateProfile")
  public ResponseEntity<String> updateProfile(
          @RequestPart("image") MultipartFile image,
          @RequestParam("nickname") String nickname,
          HttpServletRequest request) {

    // 1. JWT 토큰 추출 및 검증
    String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      return ResponseEntity.status(401).body("JWT Token is missing or invalid.");
    }
    String token = authorizationHeader.substring(7); // "Bearer " 이후의 토큰

    String email = jwtUtil.getUserEmail(token); //토큰에서 email 추출A
    String provider = jwtUtil.getUserProvider(token);// 토큰에서 provider 추출

    // 2. 사용자 검증 및 프로필 업데이트 로직
    UserVO user = usersService.findUserByEmailandProvider(email,provider);

    Long userId = user.getUserId();
   // 이미지 파일 이름 저장
    usersService.updateUserProfile(userId, nickname, image);

    return ResponseEntity.ok("Profile updated successfully.");
  }

  @GetMapping("/getUserInfo")
  public UserVO getUserInfo(@RequestParam Long userId) {
    return usersService.findUserById(userId);
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

  // 회원 탈퇴
  @DeleteMapping("/delete")
  public void deleteUser(@RequestParam String email) {
    usersService.deleteUser(email);
  }

  // 1. 리워드 증가
  // 인증 커뮤니티에서 인증 시 Users 테이블의 특정 사용자의 reward 올리기
  @PostMapping("/increment-reward")
  public void incrementUserReward(@RequestParam Long userId, int userReward) {
    usersService.incrementUserReward(userId, userReward);
  }

  // 아이디로 특정 사용자 찾기
  @GetMapping("/mypage")
  public UserVO findUserById(@RequestParam Long userId) {
    return usersService.findUserById(userId);
  }

  // 프로필 변경 - 닉네임 변경
  @PutMapping("/updateNickname")
  public void updateUser(@RequestParam String nickname, @RequestParam Long userId) {
    usersService.updateUser(nickname, userId);
  }

  // 프로필 변경 - 이미지 변경
  @PostMapping("/updateImage")
  public ResponseEntity<String> updateUser2(@RequestParam(value = "image", required = false) MultipartFile image,
                                            HttpServletRequest request) {

    String userIdHeader = request.getHeader("userId");
    if (userIdHeader == null) {
      return ResponseEntity.status(400).body("사용자 ID가 필요합니다.");
    }

    Long userId;
    try {
      userId = Long.parseLong(userIdHeader);
    } catch (NumberFormatException e) {
      return ResponseEntity.status(400).body("유효하지 않은 사용자 ID입니다.");
    }

    usersService.updateUserProfileWithImage(userId, image);
    return ResponseEntity.ok("Profile image updated successfully.");
  }
}
