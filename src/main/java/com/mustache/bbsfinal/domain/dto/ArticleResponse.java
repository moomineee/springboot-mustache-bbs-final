package com.mustache.bbsfinal.domain.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {

    private Long id;
    private String title;
    private String content;
}
