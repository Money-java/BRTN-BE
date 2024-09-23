package com.example.backend.PostCommunity.mapper;

import com.example.backend.PostCommunity.vo.PostCommunityVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PostCommunityMapper {
  void insertPost(PostCommunityVO post);
  PostCommunityVO selectPostById(Long postId);
  List<PostCommunityVO> selectAllPosts();
  void updatePost(PostCommunityVO post);
  void deletePost(Long postId);
}
