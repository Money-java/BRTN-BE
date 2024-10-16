package com.example.backend.PostCommunity.mapper;

import com.example.backend.PostCommunity.vo.PostCommunityVO;
import com.example.backend.Users.vo.UserVO;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface PostCommunityMapper {

  // 4. 인증커뮤니티 인증한 습관리스트 조회
  // 인증커뮤니티 페이지의 My Shots탭, Explore탭에서 사용
  List<PostCommunityVO> selectPostsByCategory(@Param("userId") Long userId,
                                              @Param("categoryTitle") String categoryTitle);

  // 9. 인증개수 조회
  // 특정 사용자가 인증한 게시글의 총 개수를 구하는 메서드
  // 인증커뮤니티 페이지 (레벨)
  int countUserCertifications(@Param("userId") Long userId);

  // 게시글 삽입
  void insertPost(PostCommunityVO post);

  // postId로 게시글 조회
  PostCommunityVO selectPostById(Long postId);

  // 모든 게시글 조회
  List<PostCommunityVO> selectAllPosts();

  // 게시글 수정
  void updatePost(PostCommunityVO post);

  // 게시글 삭제
  void deletePost(Long postId);

  // 진행 중인 루틴 가져오기
  List<PostCommunityVO> selectActiveHabitByUserId(Long userId);

  // 특정 달에 해당하는 이미지 가져오기
  List<PostCommunityVO> selectHabitImagesByMonth(
          @Param("userId") Long userId,
          @Param("month") int month,
          @Param("year") int year,
          @Param("habitId") Long habitId
  );

  /**
   * 월에 해당하는 좋아요가 가장 많은 이미지를 가져오는 메서드
   * @param userId
   * @param month
   * @param year
   * @return
   */
  List<PostCommunityVO> getMostLikedImagesByDate(
          @Param("userId") Long userId,
          @Param("month") int month,
          @Param("year") int year
  );

  /**
   * 날짜에 해당하는 가장 종아요 많이 받은 포스트
   * @param userId
   * @param dt
   * @param month
   * @param year
   * @return
   */
  PostCommunityVO getMostLikedImagesByDate2(
          @Param("userId") Long userId,

          @Param("year") int year,
          @Param("month") int month,
          @Param("dt") int dt
  );

  UserVO getUserInfoByPostId(@Param("postId")Long postId );
}
