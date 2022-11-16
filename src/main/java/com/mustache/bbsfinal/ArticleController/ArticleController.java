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
    public String listPage(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @GetMapping("")
    public String home(){
        return "redirect:/articles/list";
    }

    @GetMapping("/new")
    public String createArticlePage() {
        return "articles/new";
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
        List<Article> list = articleRepository.findAll();
        model.addAttribute("articles", list);
        return "articles/list";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(!optionalArticle.isEmpty()) {
            model.addAttribute("article", optionalArticle.get());
            return "edit";
        } else {
            return "error";
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return "redirect:/articles/list";
    }

    @PostMapping("")
    public String createArticle(ArticleDto articleDto) {
        Article savedArticle = articleRepository.save(articleDto.toEntitiy());
        return "redirect:/articles/" + savedArticle.getId();
    }
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDto articleDto) {
        log.info("title : {} content : {}", articleDto.getTitle(), articleDto.getContent());
        Article article = articleRepository.save(articleDto.toEntitiy());
        return "redirect:/articles/" + article.getId();
    }
}
