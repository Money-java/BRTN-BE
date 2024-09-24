package com.example.backend.Users.mapper;

import com.example.backend.Users.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

public interface UserMapper {
  void insertUser(UserVO user);
  UserVO selectUserById(Long userId);
  List<UserVO> selectAllUsers();
  void updateUser(UserVO user);
  void deleteUser(Long userId);
}
