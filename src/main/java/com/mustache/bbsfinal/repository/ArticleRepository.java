package com.mustache.bbsfinal.repository;

import com.mustache.bbsfinal.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
