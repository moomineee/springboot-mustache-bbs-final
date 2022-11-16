package com.mustache.bbsfinal.ArticleController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.bbsfinal.domain.dto.ArticleAddRequest;
import com.mustache.bbsfinal.domain.dto.ArticleAddResponse;
import com.mustache.bbsfinal.domain.dto.ArticleDto;
import com.mustache.bbsfinal.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("1개의 Json 형태로 잘 나오는지")
    void findSingle() throws Exception{
        long articleId=1l;

        given(articleService.getArticleById(articleId))
                .willReturn(new ArticleDto(1l, "룰루", "랄라"));

        String url = String.format("/api/v1/articles/%d", articleId);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());

        verify(articleService).getArticleById(articleId);
    }
    @Test
    @DisplayName("add 기능 테스트")
    void add() throws Exception {
        ArticleAddRequest dto = new ArticleAddRequest("제목", "내용");

        given(articleService.add(any()))
                .willReturn(new ArticleAddResponse(1l, dto.getTitle(), dto.getContent()));

        mockMvc.perform(post("/api/v1/articles")
                        .contentType(MediaType.APPLICATION_JSON) //
                        .content(objectMapper.writeValueAsBytes(new ArticleAddRequest("제목", "내용"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());
//        verify(articleService).add(any());
    }
}