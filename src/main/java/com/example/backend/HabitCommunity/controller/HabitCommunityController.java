package com.example.backend.HabitCommunity.controller;

import com.example.backend.HabitCommunity.service.HabitCommunityService;
import com.example.backend.HabitCommunity.vo.HabitCommunityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routine-community")
public class HabitCommunityController {

  private final HabitCommunityService habitCommunityService;

  // 1. 습관리스트 조회1 (동적 정렬: 최신순, 좋아요순, 참여자순, 달성자순)
  @GetMapping("/sort/{sortType}")
  public List<HabitCommunityVO> selectHabitList(@RequestParam(required = false) String categoryName,
                                                @PathVariable String sortType) {
    return habitCommunityService.selectHabitList(categoryName, sortType);
  }

  // 2. 습관리스트 조회2
  // 내가 좋아요한 루틴 조회
  @GetMapping("/liked")
  public List<HabitCommunityVO> selectLikedCommunities(@RequestParam String userId) {
    return habitCommunityService.selectLikedCommunities(userId);
  }

  @Autowired
  public HabitCommunityController(HabitCommunityService habitCommunityService) {
    this.habitCommunityService = habitCommunityService;
  }

  // HabitCommunity 삽입
  @PostMapping
  public void insertHabitCommunity(@RequestBody HabitCommunityVO habitCommunity) {
    habitCommunityService.insertHabitCommunity(habitCommunity);
  }

  // 특정 ID로 HabitCommunity 조회
  @GetMapping("/{id}")
  public HabitCommunityVO selectHabitCommunityById(@PathVariable Long id) {
    return habitCommunityService.selectHabitCommunityById(id);
  }

  // 모든 HabitCommunity 조회
  @GetMapping
  public List<HabitCommunityVO> selectAllHabitCommunities() {
    return habitCommunityService.selectAllHabitCommunities();
  }

  // HabitCommunity 업데이트
  @PutMapping
  public void updateHabitCommunity(@RequestBody HabitCommunityVO habitCommunity) {
    habitCommunityService.updateHabitCommunity(habitCommunity);
  }

  // HabitCommunity 삭제
  @DeleteMapping("/{id}")
  public void deleteHabitCommunity(@PathVariable Long id) {
    habitCommunityService.deleteHabitCommunity(id);
  }

}
