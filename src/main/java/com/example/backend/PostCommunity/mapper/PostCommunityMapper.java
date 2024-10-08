package com.example.backend.PostCommunity.mapper;

import com.example.backend.PostCommunity.vo.PostCommunityVO;
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


}
