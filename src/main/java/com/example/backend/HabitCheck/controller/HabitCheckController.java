package com.example.backend.HabitCheck.controller;

import com.example.backend.HabitCheck.service.HabitCheckService;
import com.example.backend.HabitCheck.vo.HabitCheckVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habit-check")
public class HabitCheckController {

  private final HabitCheckService habitCheckService;

  @Autowired
  public HabitCheckController(HabitCheckService habitCheckService) {
    this.habitCheckService = habitCheckService;
  }

  @PostMapping
  public void insertHabitCheck(@RequestBody HabitCheckVO habitCheck) {
    habitCheckService.insertHabitCheck(habitCheck);
  }

  @GetMapping("/{id}")
  public HabitCheckVO selectHabitCheckById(@PathVariable Long id) {
    return habitCheckService.selectHabitCheckById(id);
  }

  @GetMapping
  public List<HabitCheckVO> selectAllHabitChecks() {
    return habitCheckService.selectAllHabitChecks();
  }
}
