package com.mystock.mygestock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "client")
public class Client extends AbstractEntity{
    @Column(name = "nom")
    private  String nom ;
    @Column(name = "prenom")
    private String prenom ;
    @Embedded
    private  Adresse adresse;
    @Column(name = "photo")
    private String photo;
    @Column(name = "mail")
    private  String mail ;
    private String numTel ;
    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeClients;

}
