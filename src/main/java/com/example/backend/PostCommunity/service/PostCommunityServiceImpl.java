package com.example.backend.PostCommunity.service;

import com.example.backend.PostCommunity.mapper.PostCommunityMapper;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class PostCommunityServiceImpl implements PostCommunityService {

  private final PostCommunityMapper postCommunityMapper;

  @Autowired
  public PostCommunityServiceImpl(PostCommunityMapper postCommunityMapper) {
    this.postCommunityMapper = postCommunityMapper;
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
  public void insertPost(PostCommunityVO postCommunityVO) {
    postCommunityMapper.insertPost(postCommunityVO);
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

}
