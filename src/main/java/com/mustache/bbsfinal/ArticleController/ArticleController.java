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
        Article savedArticle = articleRepository.save(articleDto.toEntitiy());
        return "redirect:/articles/"+savedArticle.getId();
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (!optArticle.isEmpty()) {
            model.addAttribute("article", optArticle.get());
            //model.addAttribute("comments", optArticle.get().getComments());
            return "articles/show";
        }
        return "articles/error";
    }
    @GetMapping("/list")
    public String findAll(Model model){
        List<Article> list=articleRepository.findAll();
        model.addAttribute("articles", list);
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
    @PostMapping("/{id}/update")
    public String updatePost(@PathVariable Long id, ArticleDto articleDto){
        log.info("title : {} content : {}", articleDto.getTitle(), articleDto.getContent());
        Article article = articleRepository.save(articleDto.toEntitiy());
        return "redirect:/articles/" + article.getId();
    }
    @GetMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id){
        articleRepository.deleteById(id);
        return "redirect:/articles/list";
    }
}
