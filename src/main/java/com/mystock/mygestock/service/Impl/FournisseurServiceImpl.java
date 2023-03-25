package com.mystock.mygestock.service.Impl;


import com.mystock.mygestock.dto.FournisseurDto;
import com.mystock.mygestock.exception.EntityNotFoundException;
import com.mystock.mygestock.exception.ErrorCodes;
import com.mystock.mygestock.exception.InvalidEntityException;
import com.mystock.mygestock.model.Fournisseur;
import com.mystock.mygestock.repository.FournisseurRepository;
import com.mystock.mygestock.service.FournisseurService;
import com.mystock.mygestock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {
   private FournisseurRepository fournisseurRepository;
    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }
    @Override
    public FournisseurDto save(FournisseurDto dto) {
        List<String> errors = FournisseurValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Fournisseur is not valid ! {}", dto);
            throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID);
        }
        Fournisseur fournisseur = FournisseurDto.toEntity(dto);
        Fournisseur savedFournisseur = fournisseurRepository.save(fournisseur);
        return FournisseurDto.fromEntity(savedFournisseur);
    }
    @Override
    public List<FournisseurDto> findAll() {
        List<Fournisseur> fournisseurs = fournisseurRepository.findAll();
        return fournisseurs.stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public FournisseurDto findById(Long id) {

            Fournisseur fournisseur = fournisseurRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Aucun fournisseur avec l'ID = " + id + " n'a été trouvé dans la base",
                            ErrorCodes.CLIENT_NOT_FOUND));
            return FournisseurDto.fromEntity(fournisseur);
        }



    @Override
    public void delete(Long id) {
        Fournisseur fournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun fournisseur avec l'ID = " + id + " n'a été trouvé dans la base",
                        ErrorCodes.CLIENT_NOT_FOUND));
        fournisseurRepository.delete(fournisseur);


    }
}
