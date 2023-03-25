package com.mystock.mygestock.validator;

import com.mystock.mygestock.dto.AdresseDto;
import com.mystock.mygestock.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AdresseValidator {
    public static List<String> validate(AdresseDto adresseDto){
        List<String> errors = new ArrayList<>();
        if (adresseDto == null ) {
            errors.add("Veuiller renseigner l'adresse 1");
            errors.add("Veuiller renseigner l'adresse 2");
            errors.add("Veuiller renseigner le pays");
            errors.add("Veuiller renseigner le code postal");
            return errors;
        }

        if (!StringUtils.hasLength(adresseDto.getAdresse1())){
            errors.add("Veuiller renseigner l'adresse 1");
        }
        if (!StringUtils.hasLength(adresseDto.getAdresse2())){
            errors.add("Veuiller renseigner l'adresse 2");
        }
        if (!StringUtils.hasLength(adresseDto.getPays())){
            errors.add("Veuiller renseigner le pays");
        }
        if (!StringUtils.hasLength(adresseDto.getAdresse1())){
            errors.add("Veuiller renseigner le code postal");
        }

        return errors;
    }
}
