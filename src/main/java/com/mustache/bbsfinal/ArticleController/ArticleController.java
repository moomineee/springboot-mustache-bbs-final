package com.mustache.bbsfinal.ArticleController;

import com.mustache.bbsfinal.domain.Article;
import com.mustache.bbsfinal.domain.dto.ArticleDto;
import com.mustache.bbsfinal.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("")
    public String home(){
        return "redirect:/articles/list";
    }
    @GetMapping("/new")
    public String createPage(){
        return "articles/new";
    }
    @PostMapping("")
    public String articles(ArticleDto articleDto){
        log.info(articleDto.getTitle());
        Article savedArticle = articleRepository.save(articleDto.toEntity());
        return String.format("redirect:/articles/" + savedArticle.getId()); //
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (!optArticle.isEmpty()) {
            model.addAttribute("article", optArticle.get());
            return "articles/show";
        }
        return "articles/error";
    }
    @GetMapping("/list")
    public String findAll(Model model){
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "articles/list";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Optional<Article> optArticle = articleRepository.findById(id);
        if (!optArticle.isEmpty()) {
            model.addAttribute("article", optArticle.get());
            return "articles/edit";
        }
        return "articles/error";
    }
    @PostMapping(value = "/{id}/update") // 수정한 게시글을 다시 DB에 저장(update)
    public String updateArticle(@PathVariable Long id, ArticleDto articleDto, Model model) {
        log.info("title:{} content:{}", articleDto.getTitle(), articleDto.getContent());
        Article article = articleRepository.save(articleDto.toEntity());
        model.addAttribute("article", article);
        return String.format("redirect:/articles/%d", article.getId());
    }
    @GetMapping(value = "/{id}/delete") // 게시글 삭제
    public String deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return "redirect:/articles";
    }
}
