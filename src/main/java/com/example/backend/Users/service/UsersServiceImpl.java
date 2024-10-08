package com.example.backend.Users.service;

import com.example.backend.ImageUpload.ImageUploadService;
import com.example.backend.Users.mapper.UserMapper;
import com.example.backend.Users.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersServiceImpl implements UsersService {

  private final UserMapper userMapper;
  private final ImageUploadService imageUploadService;

  @Autowired
  public UsersServiceImpl(UserMapper userMapper, ImageUploadService imageUploadService) {
    this.userMapper = userMapper;
      this.imageUploadService = imageUploadService;
  }

  // 신규 사용자 등록
  @Override
  public void insertUser(UserVO userVO) {
    userMapper.insertUser(userVO);
  }

  // 주어진 이메일을 사용해 기존 사용자 존재 여부 확인
  @Override
  public UserVO findUserByEmailandProvider(String email, String provider) {
    return userMapper.findUserByEmailandProvider(email,provider);
  }

  @Override
  public void updateUserProfile(Long userId, String nickname, String avatar) {

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

  @Override
  public void updateUserProfile(Long userId, String nickname, MultipartFile image){

    String avatar = null;
    if(image!=null){
      avatar = imageUploadService.saveFile(image);
    }
    userMapper.updateUserProfile(userId, nickname, avatar);
  }

  // 회원 탈퇴
  @Override
  public void deleteUser(String email) {
    userMapper.deleteUser(email);
  }

  // 1. 리워드 증가
  // 인증 커뮤니티에서 인증 시 Users 테이블의 특정 사용자의 reward 올리기
  @Override
  public void incrementUserReward(Long userId, int userReward) {
    userMapper.incrementUserReward(userId, userReward);
  }

  // 아이디로 특정 사용자 찾기
  public UserVO findUserById(Long userId) {
    return userMapper.findUserById(userId);
  }

  // 닉네임 변경
  @Override
  public void updateUser(String nickname, Long userId) {
    Map<String, Object> params = new HashMap<>();
    params.put("nickname", nickname);
    params.put("userId", userId);

    userMapper.updateUser(params);
  }

  // 프로필 변경 - 이미지 변경
  @Override
  public void updateUserProfileWithImage(Long userId, MultipartFile image){

    String avatar = null;
    if(image!=null){
      avatar = imageUploadService.saveFile(image);
    }
    userMapper.updateUserProfileWithImage(userId, avatar);
  }
}
