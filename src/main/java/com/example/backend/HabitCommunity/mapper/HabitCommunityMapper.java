package com.example.backend.HabitCommunity.mapper;

import com.example.backend.HabitCommunity.vo.HabitCommunityVO;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface HabitCommunityMapper {

  // 2. 습관리스트 조회2
  // 내가 좋아요한 루틴 조회
  List<HabitCommunityVO> selectLikedCommunities(@Param("userId") String userId);

  // 새로운 습관을 MyHabit 테이블에 추가하는 쿼리 (도전하기 기능)
  void addHabitToMyHabit(@Param("userId") Long userId, @Param("habitId") Long habitId);

  // 8. 습관검색기능 
  // 루틴커뮤니티 페이지
  List<HabitCommunityVO> searchHabitCommunities(Map<String, Object> params);


  // 좋아요 추가
  void addLike(@Param("userId") Long userId, @Param("communityId") Long communityId);

  // 좋아요 취소
  void removeLike(@Param("userId") Long userId, @Param("communityId") Long communityId);

  // habit_likes 증가
  void incrementHabitLikes(@Param("communityId") Long communityId);

  // habit_likes 감소
  void decrementHabitLikes(@Param("communityId") Long communityId);


  // HabitCommunity 삽입
  void insertHabitCommunity(HabitCommunityVO habitCommunity);

  // communityId로 HabitCommunity 조회
  HabitCommunityVO selectHabitCommunityById(Long communityId);

  // 모든 HabitCommunity 조회
  List<HabitCommunityVO> selectAllHabitCommunities();

  // HabitCommunity 수정
  void updateHabitCommunity(HabitCommunityVO habitCommunity);

  // HabitCommunity 삭제
  void deleteHabitCommunity(Long communityId);

  List<PostCommunityVO> selectPostsByHabitId(Long habitId);

  // 총 HabitCommunity의 루틴 개수를 구하는 메서드
  int countHabitCommunities(Map<String, Object> params);
}
