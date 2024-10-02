package com.example.backend.Habit.controller;

import com.example.backend.Habit.dto.HabitCheckCountDTO;
import com.example.backend.Habit.dto.HabitCheckRequestDTO;
import com.example.backend.Habit.mapper.MyHabitMapper;
import com.example.backend.Habit.service.HabitService;
import com.example.backend.Habit.service.HabitServieImp;
import com.example.backend.Habit.vo.HabitCheckVO;
import com.example.backend.Habit.vo.MyHabitVO;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
import com.example.backend.exception.*;
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
  private final MyHabitMapper myHabitMapper;

  @Autowired
  public HabitController(HabitService habitService, MyHabitMapper myHabitMapper) {
    this.habitService = habitService;
    this.myHabitMapper = myHabitMapper;
  }

  // 1. 나의 습관 조회
  @GetMapping
  public ResponseEntity<List<MyHabitVO>> getMyHabit(@RequestParam("userId") long userId) {
    try {
      List<MyHabitVO> habits = habitService.getMyHabit(userId);
      log.info("(1) Successfully retrieved my habits.");
      return ResponseEntity.ok(habits);
    } catch (UnauthorizedException e) {
      log.info("401 Unauthorized: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    } catch (NotFoundException e) {
      log.info("404 Not Found: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (InternalServerErrorException e) {
      log.info("500 Internal Server Error: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  // 2. 습관 달성 체크
  @PostMapping("/checked")
  public ResponseEntity<String> addHabitChecked(@RequestBody HabitCheckVO habitCheckVO) {
    try {
      habitService.addHabitChecked(habitCheckVO);
      log.info("(2) Successfully completed the habit achievement check");
      return ResponseEntity.ok("(2) Successfully completed the habit achievement check");
    } catch (UnauthorizedException e) {
      log.info("401 Unauthorized: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    } catch (NotFoundException e) {
      log.info("404 Not Found: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (InternalServerErrorException e) {
      log.info("500 Internal Server Error: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  // 3. 달성한 습관 조회
  @GetMapping("/checked")
  public ResponseEntity<List<MyHabitVO>> getCheckedHabit(@RequestParam long userId, @RequestParam(required = false) String checkDate) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date dt;

    try {
      if (checkDate == null) {
        checkDate = sdf.format(new Date());
      }

      dt = sdf.parse(checkDate);
      log.info("Parsed date: " + dt);

      List<MyHabitVO> checkedHabits = habitService.getCheckedHabit(userId, checkDate);
      log.info("(3) Successfully retrieved the checked habits.");
      return ResponseEntity.ok(checkedHabits);
    } catch (UnauthorizedException e) {
      log.info("401 Unauthorized: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    } catch (NotFoundException e) {
      log.info("404 Not Found: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (InternalServerErrorException e) {
      log.info("500 Internal Server Error: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  // 4. 새로운 습관 작성
  @PostMapping("/add/my")
  public ResponseEntity<String> addMyHabit(@RequestBody MyHabitVO myHabitVO) {
    try {
      habitService.createHabitWithMyHabit(myHabitVO);
      log.info("(4) Successfully created a new habit");
      return ResponseEntity.ok("(4) Successfully created a new habit");
    } catch (BadRequestException e) {
      log.info("400 Bad request: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    } catch (UnauthorizedException e) {
      log.info("401 Unauthorized: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    } catch (ConflictException e) {
      log.info("409 Conflict: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    } catch (InternalServerErrorException e) {
      log.info("500 Internal Server Error: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  // 5. 습관 수정
  @PutMapping("/update")
  public ResponseEntity<String> modifyMyHabit(@RequestBody MyHabitVO myHabitVO) {
    try {
      String resultMessage = habitService.modifyMyHabit(myHabitVO);
      log.info(resultMessage);
      return ResponseEntity.ok(resultMessage);
    } catch (BadRequestException e) {
      log.info("400 Bad request: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    } catch (NotFoundException e) {
      log.info("404 Not Found: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (InternalServerErrorException e) {
      log.info("500 Internal Server Error: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The habit does not belong to this user");
    }
  }

  // 6. 습관 삭제
  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteMyHabit(@RequestBody MyHabitVO myHabitVO) {
    try {
      habitService.deleteMyHabit(myHabitVO);
      log.info("(6) Successfully deleted habit");
      return ResponseEntity.ok("(6) Successfully deleted habit");
    } catch (BadRequestException e) {
      log.info("400 Bad request: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    } catch (NotFoundException e) {
      log.info("404 Not Found: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (InternalServerErrorException e) {
      log.info("500 Internal Server Error: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  // 7. 습관 상태 변경
  @PutMapping("/update/state")
  public ResponseEntity<String> modifyMyHabitState(@RequestBody List<MyHabitVO> habits, @RequestParam long userId) {
    try {
      habitService.modifyMyHabitState(habits, userId);
      log.info("(7) Successfully updated habit state");
      return ResponseEntity.ok("(7) Successfully updated habit state");
    } catch (BadRequestException e) {
      log.info("400 Bad request: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    } catch (NotFoundException e) {
      log.info("404 Not Found: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (InternalServerErrorException e) {
      log.info("500 Internal Server Error: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  // 8. 절약 예상 금액
  @GetMapping("/save/expection")
  public ResponseEntity<Integer> saveTotalAmount() {
    try {
      int amount = habitService.saveTotalAmount();
      log.info("(8) Successfully retrieved the expected amount you can save today");
      return ResponseEntity.ok(amount);
    } catch (BadRequestException e) {
      log.info("400 Bad request: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    } catch (NotFoundException e) {
      log.info("404 Not Found: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (InternalServerErrorException e) {
      log.info("500 Internal Server Error: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  // 9. 실제 절약 금액
  @GetMapping("/save/real")
  public ResponseEntity<Integer> saveRealAmount(@RequestParam long userId) {
    try {
      int amount = habitService.saveRealAmount(userId);
      log.info("(9) Successfully retrieved today's actual savings amount");
      return ResponseEntity.ok(amount);
    } catch (BadRequestException e) {
      log.info("400 Bad request: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    } catch (NotFoundException e) {
      log.info("404 Not Found: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (InternalServerErrorException e) {
      log.info("500 Internal Server Error: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  // 10. 습관 커뮤니티 업로드
  @PostMapping("/upload/habit")
  public ResponseEntity<String> addHabitCommunity(@RequestParam long habitId) {
    try {
      habitService.addHabitCommunity(habitId);
      log.info("(10) Successfully uploaded to Habit Community");
      return ResponseEntity.ok("(10) Successfully uploaded to Habit Community");
    } catch (BadRequestException e) {
      log.info("400 Bad request: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    } catch (NotFoundException e) {
      log.info("404 Not Found: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (ConflictException e) {
      log.info("409 Conflict: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    } catch (InternalServerErrorException e) {
      log.info("500 Internal Server Error: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  // 11. 인증 커뮤니티에 업로드하기
  @PostMapping("/upload/post")
  public ResponseEntity<String> addPostCommunity(@RequestBody PostCommunityVO postCommunityVO) {
    try {
      if (postCommunityVO.getImageURL() == null) {
        postCommunityVO.setImageURL(null);
      }

      habitService.addPostCommunity(postCommunityVO);
      log.info("(11) Successfully uploaded to Post Community");
      return ResponseEntity.ok("(11) Successfully uploaded to Post Community");
    } catch (BadRequestException e) {
      log.info("400 Bad request: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    } catch (NotFoundException e) {
      log.info("404 Not Found: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (ConflictException e) {
      log.info("409 Conflict: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    } catch (InternalServerErrorException e) {
      log.info("500 Internal Server Error: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }
}
