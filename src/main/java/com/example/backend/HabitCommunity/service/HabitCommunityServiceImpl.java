package com.example.backend.HabitCommunity.service;

import com.example.backend.HabitCommunity.mapper.HabitCommunityMapper;
import com.example.backend.HabitCommunity.vo.HabitCommunityVO;
import com.example.backend.Transaction.service.TransactionService;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional  // 트랜잭션을 사용하는 경우 추가
public class HabitCommunityServiceImpl implements HabitCommunityService {

  private final HabitCommunityMapper habitCommunityMapper;

  @Autowired
  public HabitCommunityServiceImpl(HabitCommunityMapper habitCommunityMapper,
                                   @Qualifier("transactionService") TransactionService transactionService) {
    System.out.println("serviceImpl");
    this.habitCommunityMapper = habitCommunityMapper;
  }

//  // 1. 습관 리스트 조회 (동적 정렬: 최신순, 좋아요순, 참여자순, 달성자순)
//  @Override
//  public List<HabitCommunityVO> selectHabitList(String categoryName, String sortType) {
//    System.out.println("Received categoryName: " + categoryName); // 로그 확인용
//    return habitCommunityMapper.selectHabitList(categoryName, sortType);
//  }

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
  public List<HabitCommunityVO> searchHabitCommunities(String categoryName, String sortType, String keyword) {
    System.out.println("Keyword: " + keyword);
    System.out.println("CategoryName: " + categoryName);
    System.out.println("SortType: " + sortType);

    // 파라미터를 Map에 담기
    Map<String, Object> params = new HashMap<>();
    params.put("categoryName", categoryName);
    params.put("sortType", sortType);
    params.put("keyword", keyword);

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

  // HabitCommunity 삽입
  @Override
  public void insertHabitCommunity(HabitCommunityVO habitCommunity) {
    habitCommunityMapper.insertHabitCommunity(habitCommunity);
  }

  // 특정 ID로 HabitCommunity 조회

  @Override
  public HabitCommunityVO selectHabitCommunityById(Long communityId) {
    return habitCommunityMapper.selectHabitCommunityById(communityId);
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

}
