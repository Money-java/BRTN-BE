package com.example.backend.Habit.controller;

import com.example.backend.Habit.dto.HabitCheckCountDTO;
import com.example.backend.Habit.dto.HabitCheckRequestDTO;
import com.example.backend.Habit.service.HabitService;
import com.example.backend.Habit.service.HabitServieImp;
import com.example.backend.Habit.vo.HabitCheckVO;
import com.example.backend.Habit.vo.MyHabitVO;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
import com.example.backend.exception.NotFoundException;
import com.example.backend.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Slf4j
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
    try {
      List<MyHabitVO> habits = habitService.getMyHabit(userId);
      System.out.println("(1) Successfully retrieved my habits.");
      return ResponseEntity.ok(habits);
    } catch (UnauthorizedException e) {
      System.out.println("401 Unauthorized: The access token is invalid or has expired.");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    } catch (NotFoundException e) {
      System.out.println("404 Not Found: The requested data could not be found.");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (Exception e) {
      System.out.println("500 Internal Server Error: An error occurred on the server.");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  // 2. 습관 달성 체크
  @PostMapping("/checked")
  public ResponseEntity<String> addHabitChecked(@RequestBody HabitCheckVO habitCheckVO) {
    try {
      habitService.addHabitChecked(habitCheckVO);
      System.out.println("(2) Successfully completed the habit achievement check.");
      return ResponseEntity.ok("(2) Successfully completed the habit achievement check.");
    } catch (UnauthorizedException e) {
      System.out.println("404 Not Found: The requested data could not be found.");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    } catch (NotFoundException e) {
      System.out.println("404 Not Found : The requested data could not be found.");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      System.out.println("500 Internal Server Error: An error occurred on the server.");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  // 3. 달성한 습관 조회
  @GetMapping("/checked")
  public ResponseEntity<List<MyHabitVO>> getCheckedHabit(@RequestParam long userId,
                                                         @RequestParam(required = false)
                                                         String checkDate) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date dt;
    try {
      dt = sdf.parse(checkDate);
      System.out.println("Parsed date: " + dt);
    } catch (ParseException e) {
      System.err.println("Date parsing failed: " + e.getMessage());
      // 추가적인 로깅 또는 에러 처리 로직을 여기에 추가
    }
//    try {
    log.info("checkDate = {}", checkDate);
    log.info("userId = {}", userId);
    log.info("habitService = {}", habitService);
    log.info("instanceof HabitServiceImp = {}", habitService instanceof HabitServieImp);
    List<MyHabitVO> checkedHabits = habitService.getCheckedHabit(userId, checkDate);
    System.out.println("(3) Successfully retrieved the checked habits.");
    return ResponseEntity.ok(checkedHabits);
//      return null;
//    } catch (UnauthorizedException e) {
//      System.out.println("401 Unauthorized: The access token is invalid or has expired.");
//      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//    } catch (NotFoundException e) {
//      System.out.println("404 Not Found: The requested data could not be found.");
//      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//    } catch (Exception e) {
//      System.out.println("500 Internal Server Error: An error occurred on the server. : ");
//
//      e.printStackTrace();
//      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//    }

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

  @PostMapping("/counts")
  public List<HabitCheckCountDTO> getHabitCheckCounts(@RequestBody HabitCheckRequestDTO habitCheckRequestDTO) {
    return habitService.getHabitCheckCounts(habitCheckRequestDTO);
  }
}
