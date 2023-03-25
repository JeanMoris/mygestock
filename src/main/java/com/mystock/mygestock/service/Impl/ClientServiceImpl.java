package com.mystock.mygestock.service.Impl;


import com.mystock.mygestock.dto.ClientDto;
import com.mystock.mygestock.exception.EntityNotFoundException;
import com.mystock.mygestock.exception.ErrorCodes;
import com.mystock.mygestock.exception.InvalidEntityException;
import com.mystock.mygestock.model.Client;
import com.mystock.mygestock.repository.ClientRepository;
import com.mystock.mygestock.service.ClientService;
import com.mystock.mygestock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
   private ClientRepository clientRepository;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Override
    public ClientDto save(ClientDto dto) {
        List<String> errors = ClientValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Client is not valid ! {}", dto);
            throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID);
        }
        Client client = ClientDto.toEntity(dto);
        Client savedClient = clientRepository.save(client);
        return ClientDto.fromEntity(savedClient);
    }
    @Override
    public List<ClientDto> findAll() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto findById(Long id) {

            Client client = clientRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Aucun client avec l'ID = " + id + " n'a été trouvé dans la base",
                            ErrorCodes.CLIENT_NOT_FOUND));
            return ClientDto.fromEntity(client);
        }



    @Override
    public void delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun client avec l'ID = " + id + " n'a été trouvé dans la base",
                        ErrorCodes.CLIENT_NOT_FOUND));
        clientRepository.delete(client);


    }
}
