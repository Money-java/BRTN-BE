package com.example.backend.HabitCommunity.controller;

import com.amazonaws.Response;
import com.example.backend.HabitCommunity.service.HabitCommunityService;
import com.example.backend.HabitCommunity.service.HabitCommunityServiceImpl;
import com.example.backend.HabitCommunity.vo.HabitCommunityVO;
import com.example.backend.HabitCommunity.vo.LikeRequestVO;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
import java.util.HashMap;
import java.util.Map;

import com.example.backend.exception.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routine-community")
public class HabitCommunityController {

  private final HabitCommunityService habitCommunityServiceImpl;/**/


  public HabitCommunityController(HabitCommunityService habitCommunityServiceImpl) {
    System.out.println("controller");
    this.habitCommunityServiceImpl = habitCommunityServiceImpl;
  }


  // 2. 습관리스트 조회2
  // 내가 좋아요한 루틴 조회
  @GetMapping("/liked")
  public List<HabitCommunityVO> selectLikedCommunities(@RequestParam String userId) {
    return habitCommunityServiceImpl.selectLikedCommunities(userId);
  }

  // 7. 도전하기 기능: 특정 습관을 MyHabit 테이블에 추가
  @PostMapping("/challenge")
  public void addHabitToMyHabit(@RequestParam Long userId, @RequestParam Long habitId, @RequestParam Long communityId) {
    habitCommunityServiceImpl.addHabitToMyHabit(userId, habitId);
    habitCommunityServiceImpl.updateHabitParticipants(communityId);
  }

  // 8. 습관검색기능
  @GetMapping("/search-or-sort")
  public  Map<String, Object> searchHabitCommunities(@RequestParam(required = false) String categoryName,
                                                        String sortType,
                                                        String keyword,
                                                        @RequestParam(required = false) Long userId,
                                                        @RequestParam(defaultValue = "1") int page,  // 기본값은 1페이지
                                                        @RequestParam(defaultValue = "10") int size) {
    System.out.println("Keyword: " + keyword);
    System.out.println("CategoryName: " + categoryName);
    System.out.println("SortType: " + sortType);
    System.out.println("UserId: " + userId);  // userId 출력
    habitCommunityServiceImpl.updateComplete();
    List<HabitCommunityVO> communities = habitCommunityServiceImpl.searchHabitCommunities(categoryName, sortType, keyword,userId, page, size);
    int totalRecords = habitCommunityServiceImpl.countHabitCommunities(categoryName, keyword);

    // 총 페이지 수 계산
    int totalPages = (int) Math.ceil((double) totalRecords / size);

    // 데이터를 Map으로 포장하여 반환
    Map<String, Object> result = new HashMap<>();
    result.put("communities", communities);  // 루틴 데이터
    result.put("totalPages", totalPages);  // 총 페이지 수
    result.put("totalRecords", totalRecords);  // 총 데이터 수

    return result;
  }







  // 특정 ID로 HabitCommunity 조회
  @GetMapping("/{id}")
  public HabitCommunityVO selectHabitCommunityById(@PathVariable Long id) {
    return habitCommunityServiceImpl.selectHabitCommunityById(id);
  }

  // 모든 HabitCommunity 조회
  @GetMapping
  public List<HabitCommunityVO> selectAllHabitCommunities() {
    return habitCommunityServiceImpl.selectAllHabitCommunities();
  }

  // HabitCommunity 업데이트
  @PutMapping
  public void updateHabitCommunity(@RequestBody HabitCommunityVO habitCommunity) {
    habitCommunityServiceImpl.updateHabitCommunity(habitCommunity);
  }

  // HabitCommunity 삭제
  @DeleteMapping("/{id}")
  public void deleteHabitCommunity(@PathVariable Long id) {
    habitCommunityServiceImpl.deleteHabitCommunity(id);
  }

  // 좋아요 추가
  @PostMapping("/like")
  public ResponseEntity<?> addLike(@RequestBody LikeRequestVO likeRequest) {
    Long userId = likeRequest.getUserId();
    Long communityId = likeRequest.getCommunityId();

    // 이미 좋아요가 눌렸는지 확인
    if (habitCommunityServiceImpl.isAlreadyLiked(userId, communityId)) {
      // 이미 좋아요 한 경우
      return ResponseEntity.badRequest().body("이미 좋아요 한 루틴입니다.");
    }

    // 좋아요 추가 로직
    habitCommunityServiceImpl.addLike(userId, communityId);
    return ResponseEntity.ok().build();
  }


//  @PostMapping("/like")
//  public ResponseEntity<?> addLike(@RequestBody LikeRequestVO likeRequest) {
//    boolean isLiked = habitCommunityServiceImpl.isAlreadyLiked(likeRequest.getUserId(), likeRequest.getCommunityId());
//
//    if (isLiked) {
//      return ResponseEntity.badRequest().body("이미 좋아요한 루틴입니다.");
//    }
//
//    habitCommunityServiceImpl.addLike(likeRequest.getUserId(), likeRequest.getCommunityId());
//    return ResponseEntity.ok().build();
//  }


  // 좋아요 취소
  @DeleteMapping("/like")
  public ResponseEntity<?> removeLike(@RequestBody LikeRequestVO likeRequest) {
    habitCommunityServiceImpl.removeLike(likeRequest.getUserId(), likeRequest.getCommunityId());
    return ResponseEntity.ok().build();
  }
  // habit_id에 맞는 post 데이터를 조회하는 API
  @GetMapping("/posts/{habitId}")
  public List<PostCommunityVO> getPostsByHabitId(@PathVariable Long habitId) {
    return habitCommunityServiceImpl.getPostsByHabitId(habitId);
  }

  @GetMapping("/find/{habitId}")
  public ResponseEntity<?> findByHabitId(@PathVariable Long habitId) {
    Long findId = habitCommunityServiceImpl.findByHabitId(habitId);
    return ResponseEntity.status(HttpStatus.OK).body(findId);
  }

  @PostMapping("/add")
  public ResponseEntity<?> addHabitToCommunity(@RequestBody HabitCommunityVO habitCommunityVO) {
    try{
      habitCommunityServiceImpl.insertHabitCommunity(habitCommunityVO);
      return ResponseEntity.status(HttpStatus.OK).body("insert successful");
    } catch (InternalServerErrorException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @GetMapping("/top-liked")
  public ResponseEntity<?> getTopLikedHabits() {
    List<HabitCommunityVO> habitList = habitCommunityServiceImpl.getTopLikedHabits();
    return ResponseEntity.status(HttpStatus.OK).body(habitList);
  }
}