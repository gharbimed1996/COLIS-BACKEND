package com.example.colis.commands.commonapi;

import com.example.colis.enums.DemandeStatus;
import lombok.Getter;


public class AccepterDemandeCommand extends BaseCommand<String> {

    @Getter private String adresse;
    @Getter private String nom;
    @Getter private Long numTel;
    @Getter private String email;
    @Getter private String description;

    public AccepterDemandeCommand(String id, String adresse, String nom, Long numTel, String email, String description) {
        super(id);
        this.adresse = adresse;
        this.nom = nom;
        this.numTel = numTel;
        this.email = email;
        this.description = description;
    }
}
