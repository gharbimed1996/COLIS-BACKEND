package com.example.colis.commands.commonapi;

import com.example.colis.enums.DemandeStatus;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


public class UpdateDemandeCommand {
    @TargetAggregateIdentifier
    @Getter private String demandeId;
    @Getter private String adresse;
    @Getter private String nom;
    @Getter private Long numTel;
    @Getter private String email;
    @Getter private String description;
    public UpdateDemandeCommand(String demandeId, String adresse, String nom, Long numTel, String email, String description) {
        this.demandeId = demandeId;
        this.adresse = adresse;
        this.nom = nom;
        this.numTel = numTel;
        this.email = email;
        this.description = description;
    }
}
