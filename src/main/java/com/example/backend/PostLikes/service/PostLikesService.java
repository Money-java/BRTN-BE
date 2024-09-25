package com.example.backend.PostLikes.service;

import com.example.backend.PostLikes.mapper.PostLikesMapper;
import com.example.backend.PostLikes.vo.PostLikesVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostLikesService {

  @Autowired
  private PostLikesMapper postLikesMapper;

  public void addPostLike(PostLikesVO postLike) {
    postLikesMapper.insertPostLike(postLike);
  }

  public List<PostLikesVO> getPostLikesByUser(Long userId) {
    return postLikesMapper.selectPostLikesByUser(userId);
  }
}
