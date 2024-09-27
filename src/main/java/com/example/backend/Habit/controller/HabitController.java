package com.example.backend.Habit.controller;

import com.example.backend.Habit.service.HabitService;
import com.example.backend.Habit.vo.HabitCheckVO;
import com.example.backend.Habit.vo.MyHabitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

  // 1. 나의 습관 조회
  @GetMapping
  public ResponseEntity<List<MyHabitVO>> getMyHabit(@RequestParam("userId") long userId) {
    List<MyHabitVO> habits = habitService.getMyHabit(userId);
    return ResponseEntity.ok(habits);
  }

  // 2. 습관 달성 체크
  @PostMapping("/checked")
  public void addHabitChecked(@RequestBody HabitCheckVO habitCheckVO) {
    habitService.addHabitChecked(habitCheckVO);
  }

  // 3. 달성한 습관 조회
  @GetMapping("/checked")
  public List<MyHabitVO> getCheckedHabits() {
    return habitService.getCheckedHabit();
  }

  // 4. 새로운 습관 체크 작성
  @PostMapping("/insert")
  public ResponseEntity<Void> addMyHabit(@RequestBody MyHabitVO myHabitVO) {
    habitService.addMyHabit(myHabitVO);
    return ResponseEntity.ok().build();
  }

  // 5. 체크된 습관 가져오기
  @PostMapping("/insert")
  public ResponseEntity<Void> addMyHabitFromOther(@RequestBody MyHabitVO myHabitVO) {
    habitService.addMyHabitFromOther(myHabitVO);
    return ResponseEntity.ok().build();
  }

  // 6. 습관 수정
  @PutMapping("/update")
  public ResponseEntity<Void> modifyMyHabit(@RequestBody MyHabitVO myHabitVO) {
    habitService.modifyMyHabit(myHabitVO);
    return ResponseEntity.ok().build();
  }

  // 7. 습관 삭제
  @DeleteMapping("/delete")
  public ResponseEntity<Void> deleteMyHabit(@RequestParam("myHabitId") long myHabitId) {
    habitService.deleteMyHabit(myHabitId);
    return ResponseEntity.ok().build();
  }

  // 8. 습관 상태를 '진행'으로 변경
  @PutMapping("/update")
  public ResponseEntity<Void> modifyMyHabitStateS(@RequestParam("myHabitId") long myHabitId) {
    habitService.modifyMyHabitStateS(myHabitId);
    return ResponseEntity.ok().build();
  }

  // 9. 습관 상태를 '대기'로 변경
  @PutMapping("/update")
  public ResponseEntity<Void> modifyMyHabitStateW(@RequestParam("myHabitId") long myHabitId) {
    habitService.modifyMyHabitStateW(myHabitId);
    return ResponseEntity.ok().build();
  }
}
