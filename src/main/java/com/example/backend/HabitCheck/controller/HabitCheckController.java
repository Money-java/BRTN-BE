package com.example.backend.HabitCheck.controller;

import com.example.backend.HabitCheck.service.HabitCheckService;
import com.example.backend.HabitCheck.vo.HabitCheckVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/habit-check")
public class HabitCheckController {

  private final HabitCheckService habitCheckService;

  @Autowired
  public HabitCheckController(HabitCheckService habitCheckService) {
    this.habitCheckService = habitCheckService;
  }

  // 달성한 습관 삽입
  @PostMapping("/check")
  public void insertHabitCheck(@RequestBody HabitCheckVO habitCheckVO) {
    habitCheckService.insertHabitCheck(habitCheckVO);
  }

  // 오늘 달성한 습관 목록 조회
  @GetMapping("/check/today/{userId}")
  public List<HabitCheckVO> getTodayHabitCheck(@PathVariable Long userId) {
    return habitCheckService.getTodayHabitCheck(userId);
  }

  // 과거 달성한 습관 목록 (특정 날짜 조회)
  @PostMapping("/check/past/date")
  public List<HabitCheckVO> getPastHabitCheckByDate(@RequestBody Map<String, Object> params) {
    return habitCheckService.getPastHabitCheckByDate(params);
  }

  // 과거 달성한 습관 목록 (1일 전 조회)
  @PostMapping("/check/past/day")
  public List<HabitCheckVO> getPastHabitCheckByDay(@RequestBody Map<String, Object> params) {
    return habitCheckService.getPastHabitCheckByDay(params);
  }

  // 과거 달성한 습관 목록 (1주 전 조회)
  @PostMapping("/check/past/week")
  public List<HabitCheckVO> getPastHabitCheckByWeek(@RequestBody Map<String, Object> params) {
    return habitCheckService.getPastHabitCheckByWeek(params);
  }

  // 과거 달성한 습관 목록 (1달 전 조회)
  @PostMapping("/check/past/month")
  public List<HabitCheckVO> getPastHabitCheckByMonth(@RequestBody Map<String, Object> params) {
    return habitCheckService.getPastHabitCheckByMonth(params);
  }

  // 과거 달성한 습관 목록 (1년 전 조회)
  @PostMapping("/check/past/year")
  public List<HabitCheckVO> getPastHabitCheckByYear(@RequestBody Map<String, Object> params) {
    return habitCheckService.getPastHabitCheckByYear(params);
  }

  // 특정 습관 조회
  @GetMapping("/check/{myHabitId}")
  public HabitCheckVO getHabitCheckById(@PathVariable Long myHabitId) {
    return habitCheckService.getHabitCheckById(myHabitId);
  }

  // 모든 습관 목록 조회
  @GetMapping("/check/all")
  public List<HabitCheckVO> getAllHabitChecks() {
    return habitCheckService.getAllHabitChecks();
  }
}
