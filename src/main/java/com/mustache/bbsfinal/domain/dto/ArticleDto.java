package com.mustache.bbsfinal.domain.dto;

import com.mustache.bbsfinal.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class ArticleDto {
    private Long id;
    private String title;
    private String content;

    public Article toEntitiy() {
       return new Article(this.id, this.title, this.content);
    }
}
