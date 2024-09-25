package com.example.backend.Habit.controller;

import com.example.backend.Habit.service.HabitService;
import com.example.backend.Habit.vo.HabitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitController {

  private final HabitService habitService;

  @Autowired
  public HabitController(HabitService habitService) {
    this.habitService = habitService;
  }

  @PostMapping
  public void insertHabit(@RequestBody HabitVO habit) {
    habitService.insertHabit(habit);
  }

  @GetMapping("/{id}")
  public HabitVO selectHabitById(@PathVariable Long id) {
    return habitService.selectHabitById(id);
  }

  @GetMapping
  public List<HabitVO> selectAllHabits() {
    return habitService.selectAllHabits();
  }

  @PutMapping
  public void updateHabit(@RequestBody HabitVO habit) {
    habitService.updateHabit(habit);
  }

  @DeleteMapping("/{id}")
  public void deleteHabit(@PathVariable Long id) {
    habitService.deleteHabit(id);
  }
}
