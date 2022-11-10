package com.mustache.bbsfinal.ArticleController;

import com.mustache.bbsfinal.domain.Article;
import com.mustache.bbsfinal.domain.dto.ArticleDto;
import com.mustache.bbsfinal.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/new")
    public String createArticlePage() {
        return "new";
    }

    @PostMapping("")
    public String createArticle(ArticleDto articleDto) {

        Article savedArticle = articleRepository.save(articleDto.toEntitiy());
        return String.format("id : %d", savedArticle.getId());

    }
}
