package com.mustache.bbsfinal.domain.dto;

import com.mustache.bbsfinal.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class ArticleDto {
    private String title;
    private String content;

    public Article toEntitiy() {
       return new Article(title, content);
    }
}
