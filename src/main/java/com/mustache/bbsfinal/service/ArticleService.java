package com.mustache.bbsfinal.service;

import com.mustache.bbsfinal.domain.Article;
import com.mustache.bbsfinal.domain.dto.ArticleAddRequest;
import com.mustache.bbsfinal.domain.dto.ArticleAddResponse;
import com.mustache.bbsfinal.domain.dto.ArticleDto;
import com.mustache.bbsfinal.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleDto getArticleById(Long id){
        Optional<Article> optArticle=articleRepository.findById(id);
        ArticleDto article = Article.of(optArticle.get());

        return article;
    }
    public ArticleAddResponse add(ArticleAddRequest dto){
        Article article = dto.toEntity(); // dto 그대로 저장할 수 없으므로 Entity로 변환
        Article savedArticle = articleRepository.save(article);
        return new ArticleAddResponse(savedArticle.getId(), savedArticle.getTitle(), savedArticle.getContent());
    }
}
