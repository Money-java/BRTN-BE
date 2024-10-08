package com.example.backend.HabitCommunity.controller;

import com.example.backend.HabitCommunity.service.HabitCommunityService;
import com.example.backend.HabitCommunity.service.HabitCommunityServiceImpl;
import com.example.backend.HabitCommunity.vo.HabitCommunityVO;
import com.example.backend.HabitCommunity.vo.LikeRequestVO;
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
    System.out.println("controlloer");
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
  public List<HabitCommunityVO> searchHabitCommunities(@RequestParam(required = false) String categoryName,
                                                       String sortType,
                                                       String keyword) {
    System.out.println("Keyword: " + keyword);
    System.out.println("CategoryName: " + categoryName);
    System.out.println("SortType: " + sortType);
    return habitCommunityServiceImpl.searchHabitCommunities(categoryName, sortType, keyword);
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

}