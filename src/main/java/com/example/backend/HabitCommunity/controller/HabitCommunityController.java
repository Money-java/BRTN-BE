package com.example.backend.HabitCommunity.controller;

import com.example.backend.HabitCommunity.service.HabitCommunityService;
import com.example.backend.HabitCommunity.vo.HabitCommunityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habit-community")
public class HabitCommunityController {

  private final HabitCommunityService habitCommunityService;

  @Autowired
  public HabitCommunityController(HabitCommunityService habitCommunityService) {
    this.habitCommunityService = habitCommunityService;
  }

  @PostMapping
  public void insertHabitCommunity(@RequestBody HabitCommunityVO habitCommunity) {
    habitCommunityService.insertHabitCommunity(habitCommunity);
  }

  @GetMapping("/{id}")
  public HabitCommunityVO selectHabitCommunityById(@PathVariable Long id) {
    return habitCommunityService.selectHabitCommunityById(id);
  }

  @GetMapping
  public List<HabitCommunityVO> selectAllHabitCommunities() {
    return habitCommunityService.selectAllHabitCommunities();
  }

  @PutMapping
  public void updateHabitCommunity(@RequestBody HabitCommunityVO habitCommunity) {
    habitCommunityService.updateHabitCommunity(habitCommunity);
  }

  @DeleteMapping("/{id}")
  public void deleteHabitCommunity(@PathVariable Long id) {
    habitCommunityService.deleteHabitCommunity(id);
  }
}
