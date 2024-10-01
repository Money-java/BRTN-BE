package com.example.backend.HabitCommunity.controller;

import com.example.backend.HabitCommunity.service.HabitCommunityServiceImpl;
import com.example.backend.HabitCommunity.vo.HabitCommunityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routine-community")
public class HabitCommunityController {

  private final HabitCommunityServiceImpl habitCommunityServiceImpl;

  // 1. 습관리스트 조회1 (동적 정렬: 최신순, 좋아요순, 참여자순, 달성자순)
  @GetMapping("/sort/{sortType}")
  public List<HabitCommunityVO> selectHabitList(@RequestParam(required = false) String categoryName,
                                                @PathVariable String sortType) {
    return habitCommunityServiceImpl.selectHabitList(categoryName, sortType);
  }

  // 2. 습관리스트 조회2
  // 내가 좋아요한 루틴 조회
  @GetMapping("/liked")
  public List<HabitCommunityVO> selectLikedCommunities(@RequestParam String userId) {
    return habitCommunityServiceImpl.selectLikedCommunities(userId);
  }

  // 7. 도전하기 기능: 특정 습관을 MyHabit 테이블에 추가
  @PostMapping("/challenge")
  public void addHabitToMyHabit(@RequestParam Long userId, @RequestParam Long habitId) {
    habitCommunityServiceImpl.addHabitToMyHabit(userId, habitId);
  }

  // 8. 습관검색기능
  @GetMapping("/search")
  public List<HabitCommunityVO> searchHabitCommunities(@RequestParam String keyword) {
    return habitCommunityServiceImpl.searchHabitCommunities(keyword);
  }
  @Autowired
  public HabitCommunityController(HabitCommunityServiceImpl habitCommunityServiceImpl) {
    this.habitCommunityServiceImpl = habitCommunityServiceImpl;
  }

  // 특정 ID로 HabitCommunity 조회
  @GetMapping("/{id}")
  public HabitCommunityVO selectHabitCommunityById(@PathVariable Long id) {
    return habitCommunityServiceImpl.selectHabitCommunityById(id);
  }

  // 모든 HabitCommunity 조회
  @GetMapping
  public List<HabitCommunityVO> selectAllHabitCommunities() {
    return habitCommunityServiceImpl.selectAllHabitCommunities();
  }

  // HabitCommunity 업데이트
  @PutMapping
  public void updateHabitCommunity(@RequestBody HabitCommunityVO habitCommunity) {
    habitCommunityServiceImpl.updateHabitCommunity(habitCommunity);
  }

  // HabitCommunity 삭제
  @DeleteMapping("/{id}")
  public void deleteHabitCommunity(@PathVariable Long id) {
    habitCommunityServiceImpl.deleteHabitCommunity(id);
  }

}