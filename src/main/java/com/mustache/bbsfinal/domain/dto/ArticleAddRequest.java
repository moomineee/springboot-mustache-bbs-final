package com.mustache.bbsfinal.domain.dto;

import com.mustache.bbsfinal.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleAddRequest {
    private String title;
    private String content;

    public Article toEntity() { // -> Article에 @Builder 추가
        Article article = Article.builder()
                .title(this.title)
                .content(this.content) // title, content 두 개만 넣고도 article 구성 가능 -> @Builder로 인해
                .build();
        return article;
    }
}
