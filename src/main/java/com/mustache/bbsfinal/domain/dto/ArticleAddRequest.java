package com.mustache.bbsfinal.domain.dto;

import com.mustache.bbsfinal.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleAddRequest {
    private String title;
    private String content;

    public Article toEntity(){
        Article article = Article.builder()
                .title(this.title)
                .content(this.content)
                .build();
        return article;
    }

}
