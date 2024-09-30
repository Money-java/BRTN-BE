package com.example.backend.Habit.controller;

import com.example.backend.Habit.service.HabitService;
import com.example.backend.Habit.vo.HabitCheckVO;
import com.example.backend.Habit.vo.MyHabitVO;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
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
  @PostMapping("/add/my")
  public void addMyHabit(@RequestBody MyHabitVO myHabitVO) {
    habitService.addMyHabit(myHabitVO);
  }

  // 5. 체크된 습관 가져오기
  @PostMapping("/add/other")
  public void addMyHabitFromOther(@RequestBody MyHabitVO myHabitVO) {
    habitService.addMyHabitFromOther(myHabitVO);
  }

  // 6. 습관 수정
  @PutMapping("/update")
  public void modifyMyHabit(@RequestBody MyHabitVO myHabitVO) {
    habitService.modifyMyHabit(myHabitVO);
  }

  // 7. 습관 삭제
  @DeleteMapping("/delete")
  public void deleteMyHabit(@RequestParam("myHabitId") long myHabitId) {
    habitService.deleteMyHabit(myHabitId);
  }

  // 8. 습관 상태를 '진행'으로 변경
  @PutMapping("/update/state/start")
  public void modifyMyHabitStateS(@RequestParam("myHabitId") long myHabitId) {
    habitService.modifyMyHabitStateS(myHabitId);
  }

  // 9. 습관 상태를 '대기'로 변경
  @PutMapping("/update/state/wait")
  public void modifyMyHabitStateW(@RequestParam("myHabitId") long myHabitId) {
    habitService.modifyMyHabitStateW(myHabitId);
  }

  // 10. 오늘 절약 가능한 예상 금액
  @GetMapping("/save/expection")
  public int saveTotalAmount() {
    return habitService.saveTotalAmount();
  }

  // 11. 실제 절약 금액
  @GetMapping("/save/real")
  public int saveRealAmount() {
    return habitService.saveRealAmount();
  }

  // 12. 습관 커뮤니티에 업로드하기
  @PostMapping("/upload/habit")
  public void addHabitCommunity(@RequestParam("userId") long userId) {
    habitService.addHabitCommunity();
  }

  // 13. 인증 커뮤니티에 업로드하기
  @PostMapping("/upload/post")
  public void addPostCommunity(@RequestBody PostCommunityVO postCommunityVO) {
    habitService.addPostCommunity();
  }
}
