package com.mustache.bbsfinal.ArticleController;

import com.mustache.bbsfinal.domain.Article;
import com.mustache.bbsfinal.domain.dto.ArticleAddRequest;
import com.mustache.bbsfinal.domain.dto.ArticleAddResponse;
import com.mustache.bbsfinal.domain.dto.ArticleDto;
import com.mustache.bbsfinal.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleRestController {

    ArticleService articleService;
    public ArticleRestController(ArticleService articleService){
        this.articleService = articleService;
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<ArticleDto> get(@PathVariable Long id){
        ArticleDto articleDto = articleService.getArticleById(id);
        return ResponseEntity
                .ok()
                .body(articleDto);
    }

    @PostMapping("")
    public ResponseEntity<ArticleAddResponse> addArticle(@RequestBody ArticleAddRequest dto){
        ArticleAddResponse response = articleService.add(dto);
        return ResponseEntity.ok().body(response);
    }
}
