package com.colis.paypal.commonApi.Event;

import com.colis.paypal.commonApi.Enum.AdStatus;
import lombok.Getter;

public class AdCreatedEvent extends baseEvent<String> {

    @Getter private long prix;
    @Getter private String nom;
    @Getter private String prenom;
    @Getter private String email;
    @Getter private long telephone;
    @Getter private AdStatus status;




    public AdCreatedEvent(String id, Long prix, String nom, String prenom , String email, long telephone, AdStatus status)
    {
        super(id);
        this.prix = prix;
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.telephone=telephone;
        this.status=status;
    }
}
