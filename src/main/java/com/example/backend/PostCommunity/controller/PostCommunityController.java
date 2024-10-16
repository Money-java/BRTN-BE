package com.example.backend.PostCommunity.controller;

import com.example.backend.PostCommunity.dto.PostCommunityRequestDTO;
import com.example.backend.PostCommunity.service.PostCommunityService;
import com.example.backend.PostCommunity.service.PostCommunityServiceImpl;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
import com.example.backend.Users.vo.UserVO;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post-community")
@CrossOrigin(origins="http://localhost:5173")
public class PostCommunityController {

  private static final Logger log = LoggerFactory.getLogger(PostCommunityController.class);
  private final PostCommunityService postCommunityService;

  @Autowired
  public PostCommunityController(PostCommunityService postCommunityService) {
    this.postCommunityService = postCommunityService;
  }

  // 4. 인증커뮤니티 인증한 습관리스트 조회
  // 인증커뮤니티 페이지의 My Shots탭, Explore탭에서 사용
  @GetMapping("/explore")
  public List<PostCommunityVO> selectPostsByCategory(@RequestParam(value = "userId", required = false) Long userId,
                                                     @RequestParam(value = "category", required = false) String categoryTitle) {
    return postCommunityService.selectPostsByCategory(userId, categoryTitle);
  }

  // 9. 인증개수 조회
  // 특정 사용자가 인증한 게시글의 총 개수를 구하는 메서드
  // 인증커뮤니티 페이지 (레벨)
  @GetMapping("/certification-count")
  public int countUserCertifications(@RequestParam Long userId) {
    return postCommunityService.countUserCertifications(userId);
  }


  @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> insertPost(@ModelAttribute PostCommunityRequestDTO requestDTO) {
    log.info("asdfasdfasdf");
    try {
      int reward = postCommunityService.insertPost(requestDTO);
      return ResponseEntity.ok().body(reward);
    } catch (Exception e) {
      // 예외 처리 및 로깅
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  // 특정 Post 조회 (by ID)
  @GetMapping("/{postId}")
  public PostCommunityVO selectPostById(@PathVariable Long postId) {
    return postCommunityService.selectPostById(postId);
  }

  // 모든 Post 조회
  @GetMapping("/all")
  public List<PostCommunityVO> selectAllPosts() {
    return postCommunityService.selectAllPosts();
  }

  // Post 업데이트
  @PutMapping("/update/{postId}")
  public void updatePost(@PathVariable Long postId, @RequestBody PostCommunityVO postCommunityVO) {
    postCommunityVO.setPostId(postId);
    postCommunityService.updatePost(postCommunityVO);
  }

  // Post 삭제
  @DeleteMapping("/delete/{postId}")
  public void deletePost(@PathVariable Long postId) {
    postCommunityService.deletePost(postId);
  }

  // 진행 중인 루틴을 가져오는 API
  @GetMapping("/myhabit/active")
  public ResponseEntity<?> getActiveHabit(@RequestParam Long userId) {
    List<PostCommunityVO> activeHabits = postCommunityService.getActiveHabitByUserId(userId);
    return ResponseEntity.ok(activeHabits);
  }


  @GetMapping("/images")
  public ResponseEntity<List<PostCommunityVO>> getImagesByMonth(
          @RequestParam Long userId,
          @RequestParam int month,
          @RequestParam int year,
          @RequestParam Long habitId) {

    // 파라미터 순서: userId, month, year, habitId
    List<PostCommunityVO> images = postCommunityService.getHabitImagesByMonth(userId, month, year, habitId);
    return ResponseEntity.ok(images);
  }
  // 날짜별로 좋아요가 가장 많은 이미지를 가져오는 API
  @GetMapping("/images/most-liked")
  public ResponseEntity<List<PostCommunityVO>> getMostLikedImagesByDate(
          @RequestParam("userId") Long userId,
          @RequestParam("month") int month,
          @RequestParam("year") int year
  ) {
    List<PostCommunityVO> mostLikedImages = postCommunityService.getMostLikedImagesByDate(userId, month, year);
    return ResponseEntity.ok(mostLikedImages);
  }

  @GetMapping("/date")
  public PostCommunityVO test(
          @RequestParam Long userId,
          @RequestParam int selectedYear,
                              @RequestParam int selectedMonth,
                              @RequestParam int selectedDt
                              ) {
log.info("date=" + selectedYear + " , " + selectedMonth + ", " + selectedDt);
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    Date dt = sdf.parse(selectedYear +"-" + selectedMonth + "-" + selectedDt);
    return postCommunityService.getMostLikedImagesByDate2(userId, selectedYear, selectedMonth, selectedDt);

  }

  @GetMapping("/getUserInfoByPostId")
  public ResponseEntity<UserVO> getUserInfoByPostId(@RequestParam Long postId) {
    UserVO userInfo = postCommunityService.getUserInfoByPostId(postId);
    if (userInfo != null) {
      return ResponseEntity.ok(userInfo);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

}

