package com.example.colis.events;

import com.example.colis.enums.ColisStatus;
import com.example.colis.enums.DemandeStatus;
import lombok.Getter;

public class DemandeUpdatedEvent extends BaseEvent<String>{
    @Getter private String adresse;
    @Getter private String nom;
    @Getter private Long numTel;
    @Getter private String email;
    @Getter private String description;
    @Getter private DemandeStatus status;

    public DemandeUpdatedEvent(String id, String adresse, String nom, Long numTel, String email, String description, DemandeStatus status) {
        super(id);
        this.adresse = adresse;
        this.nom = nom;
        this.numTel = numTel;
        this.email = email;
        this.description = description;
        this.status = status;
    }
}
