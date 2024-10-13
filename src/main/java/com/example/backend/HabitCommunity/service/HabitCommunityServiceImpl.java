package com.example.backend.HabitCommunity.service;

import com.example.backend.HabitCommunity.mapper.HabitCommunityMapper;
import com.example.backend.HabitCommunity.vo.HabitCommunityVO;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.PostCommunity.vo.PostCommunityVO;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional  // 트랜잭션을 사용하는 경우 추가
public class HabitCommunityServiceImpl implements HabitCommunityService {

  private final HabitCommunityMapper habitCommunityMapper;

  @Autowired
  public HabitCommunityServiceImpl(HabitCommunityMapper habitCommunityMapper
                                  ) {
    System.out.println("serviceImpl");
    this.habitCommunityMapper = habitCommunityMapper;
  }


  // 2. 습관리스트 조회2
  // 내가 좋아요한 루틴 조회
  @Override
  public List<HabitCommunityVO> selectLikedCommunities(String userId) {
    return habitCommunityMapper.selectLikedCommunities(userId);
  }


   // 7. 도전하기 기능(스크랩)
   // 다른 사람이 공유한 습관을 보고 도전하기 버튼을 누르면 나의 습관으로 추가
   // 루틴공유커뮤니티 페이지
  @Override
  public void addHabitToMyHabit(Long userId, Long habitId) {
    habitCommunityMapper.addHabitToMyHabit(userId, habitId);
  }

  // 8. 습관검색기능
  // 루틴커뮤니티 페이지
  @Override
  public List<HabitCommunityVO> searchHabitCommunities(String categoryName, String sortType, String keyword, Long userId, int page, int size) {
    System.out.println("Keyword: " + keyword);
    System.out.println("CategoryName: " + categoryName);
    System.out.println("SortType: " + sortType);

    int offset = (page - 1) * size;  // 페이지네이션에서 offset 계산

    // 파라미터를 Map에 담기
    Map<String, Object> params = new HashMap<>();
    params.put("categoryName", categoryName);
    params.put("sortType", sortType);
    params.put("keyword", keyword);
    params.put("userId", userId);
    params.put("size", size);
    params.put("offset", offset);

    // Map을 매퍼에 전달
    return habitCommunityMapper.searchHabitCommunities(params);
  }


//  // 좋아요 추가
//  @Override
//  public void addLike(Long userId, Long communityId) {
//    // 좋아요 추가 로직
//    habitCommunityMapper.addLike(userId, communityId);
//  }
//
//  // 좋아요 취소
//  @Override
//  public void removeLike(Long userId, Long communityId) {
//    // 좋아요 취소 로직
//    habitCommunityMapper.removeLike(userId, communityId);
//  }




  @Override
  @Transactional
  public void addLike(Long userId, Long communityId) {
    habitCommunityMapper.addLike(userId, communityId);
    log.info("Added like for communityId: " + communityId);
    habitCommunityMapper.incrementHabitLikes(communityId); // 좋아요 증가
  }

  @Override
  @Transactional
  public void removeLike(Long userId, Long communityId) {
    habitCommunityMapper.removeLike(userId, communityId);
    habitCommunityMapper.decrementHabitLikes(communityId); // 좋아요 감소
  }

  @Override
  public boolean isAlreadyLiked(Long userId, Long communityId) {
//    Map<String, Object> params = new HashMap<>();
//    params.put("userId", userId);
//    params.put("communityId", communityId);
    return habitCommunityMapper.countUserLike(userId, communityId) > 0;
  }

  // HabitCommunity 삽입
  @Override
  public void insertHabitCommunity(HabitCommunityVO habitCommunity) {
    habitCommunityMapper.insertHabitCommunity(habitCommunity);
  }

  // 특정 CommunityID로 HabitCommunity 조회
  @Override
  public HabitCommunityVO selectHabitCommunityById(Long communityId) {
    return habitCommunityMapper.selectHabitCommunityById(communityId);
  }

  //특정 HabitId로 HabitCommunity 조회
  @Override
  public Long findByHabitId(Long habitId){
    Long findId = habitCommunityMapper.findByHabitId(habitId);
    if(findId == null){
      return 0L;
    }
    else return findId;
  }
  // 모든 HabitCommunity 조회
  @Override
  public List<HabitCommunityVO> selectAllHabitCommunities() {
    return habitCommunityMapper.selectAllHabitCommunities();
  }

  // HabitCommunity 업데이트

  @Override
  public void updateHabitCommunity(HabitCommunityVO habitCommunity) {
    habitCommunityMapper.updateHabitCommunity(habitCommunity);
  }

  // HabitCommunity 삭제

  @Override
  public void deleteHabitCommunity(Long communityId) {
    habitCommunityMapper.deleteHabitCommunity(communityId);
  }

  //SHOT PREVIEW
  @Override
  public List<PostCommunityVO> getPostsByHabitId(Long habitId) {
    return habitCommunityMapper.selectPostsByHabitId(habitId);
  }

  @Override
  public int countHabitCommunities(String categoryName, String keyword) {
    Map<String, Object> params = new HashMap<>();
    params.put("categoryName", categoryName);
    params.put("keyword", keyword);
    return habitCommunityMapper.countHabitCommunities(params);
  }

  @Override
  public List<HabitCommunityVO> getTopLikedHabits(){
    return habitCommunityMapper.getTopLikedHabits();
  }
}
