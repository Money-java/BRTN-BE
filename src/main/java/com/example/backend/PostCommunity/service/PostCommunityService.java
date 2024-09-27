package com.example.backend.PostCommunity.service;

import com.example.backend.PostCommunity.vo.PostCommunityVO;

import java.util.List;

public interface PostCommunityService {

    // PostCommunity 삽입
    void insertPost(PostCommunityVO post);
    // 특정 ID로 PostCommunity 조회
    PostCommunityVO selectPostById(Long postId);
    // 모든 PostCommunity 조회
    List<PostCommunityVO> selectAllPosts();
    // PostCommunity 업데이트
    void updatePost(PostCommunityVO post);
    // PostCommunity 삭제
    void deletePost(Long postId);
}
