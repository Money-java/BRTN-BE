package com.example.backend.PostCommunity.service;

import com.example.backend.ImageUpload.ImageUploadService;
import com.example.backend.PostCommunity.dto.PostCommunityRequestDTO;
import com.example.backend.PostCommunity.mapper.PostCommunityMapper;
import com.example.backend.PostCommunity.vo.PostCommunityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class PostCommunityServiceImpl implements PostCommunityService {

  private final PostCommunityMapper postCommunityMapper;
  private final ImageUploadService imageUploadService;

  @Autowired
  public PostCommunityServiceImpl(PostCommunityMapper postCommunityMapper, ImageUploadService imageUploadService) {
    this.postCommunityMapper = postCommunityMapper;
    this.imageUploadService = imageUploadService;
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
  public void insertPost(PostCommunityRequestDTO requestDTO) {
    // DTO에서 VO로 변환
    PostCommunityVO postCommunityVO = convertToVO(requestDTO);

    //  MyBatis Mapper를 통해 데이터베이스에 저장
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

  private PostCommunityVO convertToVO(PostCommunityRequestDTO requestDTO) {
    PostCommunityVO postCommunityVO = new PostCommunityVO();
    postCommunityVO.setUserId(requestDTO.getUserId());
    postCommunityVO.setHabitId(requestDTO.getHabitId());
    postCommunityVO.setContent(requestDTO.getContent());
    postCommunityVO.setHashtag(requestDTO.getHashtag());

    // S3에 이미지 업로드 및 URL 설정
    String imageUrl = null;
    if (requestDTO.getImage() != null && !requestDTO.getImage().isEmpty()) {
      imageUrl = imageUploadService.saveFile(requestDTO.getImage());
    }
    postCommunityVO.setImageURL(imageUrl);

    return postCommunityVO;
  }
}
