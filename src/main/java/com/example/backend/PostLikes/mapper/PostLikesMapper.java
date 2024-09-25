package com.example.backend.PostLikes.mapper;

import com.example.backend.PostCommunity.vo.PostCommunityVO;
import com.example.backend.PostLikes.vo.PostLikesVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostLikesMapper {
  void insertPostLike(PostLikesVO postLike);

  List<PostLikesVO> selectPostLikesByUser(Long userId);

}