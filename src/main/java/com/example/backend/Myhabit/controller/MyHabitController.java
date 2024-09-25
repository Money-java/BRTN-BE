package com.example.backend.Myhabit.controller;

import com.example.backend.Myhabit.service.MyHabitService;
import com.example.backend.Myhabit.vo.MyHabitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myhabits")
public class MyHabitController {

  private final MyHabitService myHabitService;

  @Autowired
  public MyHabitController(MyHabitService myHabitService) {
    this.myHabitService = myHabitService;
  }

  // MyHabit 생성
  @PostMapping
  public void insertMyHabit(@RequestBody MyHabitVO myHabit) {
    myHabitService.insertMyHabit(myHabit);
  }

  // 특정 ID로 MyHabit 조회
  @GetMapping("/{id}")
  public MyHabitVO selectMyHabitById(@PathVariable Long id) {
    return myHabitService.selectMyHabitById(id);
  }

  // 모든 MyHabit 조회
  @GetMapping
  public List<MyHabitVO> selectAllMyHabits() {
    return myHabitService.selectAllMyHabits();
  }

  // MyHabit 업데이트
  @PutMapping
  public void updateMyHabit(@RequestBody MyHabitVO myHabit) {
    myHabitService.updateMyHabit(myHabit);
  }

  // MyHabit 삭제
  @DeleteMapping("/{id}")
  public void deleteMyHabit(@PathVariable Long id) {
    myHabitService.deleteMyHabit(id);
  }

  // 대기 상태(OFF)인 습관 조회
  @GetMapping("/state/off")
  public List<MyHabitVO> selectHabitsByStateOff() {
    return myHabitService.selectHabitsByStateOff();
  }

  // 진행 상태(ON)인 습관 조회
  @GetMapping("/state/on")
  public List<MyHabitVO> selectHabitsByStateOn() {
    return myHabitService.selectHabitsByStateOn();
  }

  // 특정 습관의 상태를 ON으로 변경
  @PutMapping("/state/on/{id}")
  public void updateHabitStateOn(@PathVariable Long id) {
    myHabitService.updateHabitStateOn(id);
  }

  // 특정 습관의 상태를 OFF로 변경
  @PutMapping("/state/off/{id}")
  public void updateHabitStateOff(@PathVariable Long id) {
    myHabitService.updateHabitStateOff(id);
  }

  // 상태가 ON인 습관 제목 조회
  @GetMapping("/titles/state/on")
  public List<String> selectHabitTitlesByState() {
    return myHabitService.selectHabitTitlesByState();
  }
}
