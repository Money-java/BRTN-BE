package com.example.backend.PostLikes.service;

import com.example.backend.PostLikes.vo.PostLikesVO;

import java.util.List;

public interface PostLikesService {

    void addPostLike(PostLikesVO postLike);
    List<PostLikesVO> getPostLikesByUser(Long userId);
}
