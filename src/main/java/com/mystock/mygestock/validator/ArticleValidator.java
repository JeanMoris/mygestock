package com.mystock.mygestock.validator;


import com.mystock.mygestock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator  {
    public static List<String> validate(ArticleDto dto){
        List<String> errors = new ArrayList<>();
        if (dto == null) {
            errors.add("Veuiller renseigner le code de l'article");
            errors.add("Veuiller renseigner la désignation de l'article");
            errors.add("Veuiller renseigner le prix unitaire de l'article");
            errors.add("Veuiller renseigner le taux TVA de l'article");
            errors.add("Veuiller renseigner le prix unitaire TTC de l'article");
            errors.add("Veuiller selectionner un catégorie");
            return errors;

        }
        if (!StringUtils.hasLength((dto.getCodeArticle()))){
            errors.add("Veuiller renseigner le code de l'article");
        }
        if (!StringUtils.hasLength((dto.getDesignation()))){
            errors.add("Veuiller renseigner la désignation de l'article");
        }
        if (dto.getPrixUnitaire() == null){
            errors.add("Veuiller renseigner le prix unitaire de l'article");
        }
        if (dto.getTauxTva() == null){
            errors.add("Veuiller renseigner le taux TVA de l'article");
        }
        if (dto.getPrixUnitaireTtc() == null){
            errors.add("Veuiller renseigner le prix unitaire TTC de l'article");
        }
        if (dto.getCategory() == null){
            errors.add("Veuiller selectionner un catégorie");
        }

        return errors;
    }
}
