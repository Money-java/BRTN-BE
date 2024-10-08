package com.example.backend.HabitCommunity.controller;

import com.example.backend.HabitCommunity.service.HabitCommunityService;
import com.example.backend.HabitCommunity.service.HabitCommunityServiceImpl;
import com.example.backend.HabitCommunity.vo.HabitCommunityVO;
import com.example.backend.HabitCommunity.vo.LikeRequestVO;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
  public void addHabitToMyHabit(@RequestParam Long userId, @RequestParam Long habitId) {
    habitCommunityServiceImpl.addHabitToMyHabit(userId, habitId);

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
    habitCommunityServiceImpl.addLike(likeRequest.getUserId(), likeRequest.getCommunityId());
    return ResponseEntity.ok().build();
  }

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
}