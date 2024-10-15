package com.example.backend.PostCommunity.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PostCommunityRequestDTO {
    private Long userId;
    private Long myHabitId;
    private Long habitId;
    private String content;
    private String hashtag;
    private MultipartFile image;
}
