package com.mystock.mygestock.repository;

import com.mystock.mygestock.dto.ArticleDto;
import com.mystock.mygestock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article , Long> {

    List<Article> findAllByCategoryId(Long idCategory);


}
