package com.example.backend.PostCommunity.service;

import com.example.backend.Habit.dto.HabitCheckCountDTO;
import com.example.backend.Habit.dto.HabitCheckRequestDTO;
import com.example.backend.Habit.mapper.HabitCheckMapper;
import com.example.backend.Habit.mapper.MyHabitMapper;
import com.example.backend.Habit.vo.HabitCheckVO;
import com.example.backend.ImageUpload.ImageUploadService;
import com.example.backend.PostCommunity.dto.PostCommunityRequestDTO;
import com.example.backend.PostCommunity.mapper.PostCommunityMapper;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
import com.example.backend.Users.mapper.UserMapper;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
public class PostCommunityServiceImpl implements PostCommunityService {

  private static final Logger log = LoggerFactory.getLogger(PostCommunityServiceImpl.class);
  private final PostCommunityMapper postCommunityMapper;
  private final MyHabitMapper myHabitMapper;
  private final HabitCheckMapper habitCheckMapper;
  private final UserMapper userMapper;

  private final ImageUploadService imageUploadService;

  @Autowired
  public PostCommunityServiceImpl(PostCommunityMapper postCommunityMapper, ImageUploadService imageUploadService, MyHabitMapper myHabitMapper, HabitCheckMapper habitCheckMapper, UserMapper userMapper) {
    this.postCommunityMapper = postCommunityMapper;
    this.imageUploadService = imageUploadService;
      this.myHabitMapper = myHabitMapper;
      this.habitCheckMapper = habitCheckMapper;
      this.userMapper = userMapper;
  }

  // 4. 인증커뮤니티 인증한 습관리스트 조회
  // 인증커뮤니티 페이지의 My Shots탭, Explore탭에서 사용
  @Override
  public List<PostCommunityVO> selectPostsByCategory(Long userId, String categoryTitle) {
    return postCommunityMapper.selectPostsByCategory(userId, categoryTitle);
  }

  // 9. 인증개수 조회
  // 특정 사용자가 인증한 게시글의 총 개수를 구하는 메서드
  // 인증커뮤니티 페이지 (레벨)
  @Override
  public int countUserCertifications(Long userId) {
    return postCommunityMapper.countUserCertifications(userId);
  }


  @Override
  @Transactional(rollbackFor = Exception.class)
  public int insertPost(PostCommunityRequestDTO requestDTO) {
    try {
      /* 게시물 저장 */
      log.info("hi: {}" , requestDTO);
      PostCommunityVO postCommunityVO = convertToPostRequestVO(requestDTO);
      postCommunityMapper.insertPost(postCommunityVO);

      /* 리워드 지급 */
      int userReward = calcUserReward(requestDTO);
      log.info("userReward: " + userReward);
      if (userReward > 0) {
        userMapper.incrementUserReward(requestDTO.getUserId(), userReward);
      }

      /* 습관 체크 */
      HabitCheckVO habitCheckVO = convertToAddHabitCheckRequestVO(requestDTO);
      habitCheckMapper.addHabitChecked(habitCheckVO);

      return userReward;
    } catch (Exception e) {
      log.error("Error occurred while inserting post: {}", e.getMessage());
      throw e; // 예외를 다시 던져 트랜잭션을 롤백합니다.
    }
  }

  @Override
  // 특정 ID로 PostCommunity 조회
  public PostCommunityVO selectPostById(Long postId) {
    return postCommunityMapper.selectPostById(postId);
  }

  @Override
  // 모든 PostCommunity 조회
  public List<PostCommunityVO> selectAllPosts() {
    return postCommunityMapper.selectAllPosts();
  }


  @Override
  public void updatePost(PostCommunityVO postCommunityVO) {
    postCommunityMapper.updatePost(postCommunityVO);
  }

  @Override
  public void deletePost(Long postId) {
    postCommunityMapper.deletePost(postId);
  }

  // AWS S3에 업로드한 이미지 링크를 활용하여 DB 저장을 위한 VO 생성
  private PostCommunityVO convertToPostRequestVO(PostCommunityRequestDTO requestDTO) {
    PostCommunityVO postCommunityVO = new PostCommunityVO();
    postCommunityVO.setUserId(requestDTO.getUserId());
    postCommunityVO.setHabitId(requestDTO.getHabitId());
    postCommunityVO.setContent(requestDTO.getContent());
    postCommunityVO.setHashtag(requestDTO.getHashtag());

    // S3에 이미지 업로드 & 이미지 url 갖고오기
    String imageUrl = null;
    if (requestDTO.getImage() != null && !requestDTO.getImage().isEmpty()) {
      imageUrl = imageUploadService.saveFile(requestDTO.getImage());
    }
    postCommunityVO.setImageURL(imageUrl);

    return postCommunityVO;
  }

  // 습관 체크 요청을 위한 VO로 변환
  private HabitCheckVO convertToAddHabitCheckRequestVO(PostCommunityRequestDTO requestDTO) {
    HabitCheckVO habitCheckVO = new HabitCheckVO();
    habitCheckVO.setHabitId(requestDTO.getHabitId()); // 습관 id
    habitCheckVO.setMyHabitId(requestDTO.getMyHabitId()); // 내 습관 id
    habitCheckVO.setUserId(requestDTO.getUserId()); // 유저 id

    return habitCheckVO;
  }

  // 습관 기록을 활용하여 사용자에게 지급할 리워드를 계산
  private int calcUserReward(PostCommunityRequestDTO requestDTO) {
    /* 습관 체크 내역 요청하기 */
    HabitCheckRequestDTO habitCheckRequestDTO = new HabitCheckRequestDTO();
    habitCheckRequestDTO.setUserId(requestDTO.getUserId());
    habitCheckRequestDTO.setHabitId(requestDTO.getHabitId());
    log.info("habitCheckRequestDTO: {}", habitCheckRequestDTO);

    /*
      continuousDateCount(N)
           : 지난 한달동안에 인증 완료한 각각의 기간 블록에서 며칠을 연속해 인증했는지를 의미
      timeDiffInfo:
          0: 오늘까지 N일 만큼 연속해서 인증 완료
          1: 어제까지 N일 만큼 연속해서 인증 완료
       else: 과거(날짜 특정 불가)에 N일 만큼 연속해서 인증 완료
     */
    List<HabitCheckCountDTO> habitCheckCountList = habitCheckMapper.getHabitCheckCounts(habitCheckRequestDTO);

    log.info("habitCheckCountList: {}", habitCheckCountList);
    /* 지급할 리워드 계산 */
    // 기록이 없는 경우 기본 리워드 지급
    if (habitCheckCountList == null || habitCheckCountList.isEmpty()) {
      return 10;
    }

    int timeDiffInfo = habitCheckCountList.get(0).getTimeDiff();
    int continuousDateCount = habitCheckCountList.get(0).getContinuousDateCount();

    log.info("timeDiffInfo: {}", timeDiffInfo);
    log.info("continuousDateCount: {}", continuousDateCount);
    // 이미 해당 루틴에 대해 인증을 완료한 경우 리워드 지급 X
    if (timeDiffInfo <= 0) {
      return 0;
    }

    // 어제 인증한 기록이 없는 경우 기본 리워드 지급
    if (timeDiffInfo != 1) {
      return 10;
    }

    // 3일 이하로 달성
    if (continuousDateCount < 3) {
      return 10;
    }

    // 4~6일 연속으로 달성
    if (continuousDateCount < 6) {
      return 20;
    }

    // 7일 이상 연속으로 달성
    return 30;
  }
  @Override
  public List<PostCommunityVO> getActiveHabitByUserId(Long userId) {
    return postCommunityMapper.selectActiveHabitByUserId(userId);
  }

  // habitId를 포함하는 이미지를 가져오는 서비스 메서드
  @Override
  public List<PostCommunityVO> getHabitImagesByMonth(Long userId, int month, int year, Long habitId) {
    return postCommunityMapper.selectHabitImagesByMonth(userId, month, year, habitId);
  }

  @Override
  public List<PostCommunityVO> getMostLikedImagesByDate(Long userId, int month, int year) {
    return postCommunityMapper.getMostLikedImagesByDate(userId, month, year);
  }
}
