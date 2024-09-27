package com.example.backend.PostCommunity.service;

import com.example.backend.PostCommunity.mapper.PostCommunityMapper;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommunityServiceImpl implements PostCommunityService {

  private final PostCommunityMapper postCommunityMapper;

  @Autowired
  public PostCommunityServiceImpl(PostCommunityMapper postCommunityMapper) {
    this.postCommunityMapper = postCommunityMapper;
  }

  // PostCommunity 삽입
  public void insertPost(PostCommunityVO post) {
    postCommunityMapper.insertPost(post);
  }

  // 특정 ID로 PostCommunity 조회
  public PostCommunityVO selectPostById(Long postId) {
    return postCommunityMapper.selectPostById(postId);
  }

  // 모든 PostCommunity 조회
  public List<PostCommunityVO> selectAllPosts() {
    return postCommunityMapper.selectAllPosts();
  }

  // PostCommunity 업데이트
  public void updatePost(PostCommunityVO post) {
    postCommunityMapper.updatePost(post);
  }

  // PostCommunity 삭제
  public void deletePost(Long postId) {
    postCommunityMapper.deletePost(postId);
  }
}
