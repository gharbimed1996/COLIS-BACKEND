package com.example.commonApi.events;

import com.example.commonApi.enums.AnnounceStatus;
import lombok.Getter;

import java.sql.Time;
import java.util.Date;

public class AnnounceUpdatedEvent extends BaseEvent<String>{

    @Getter private String reference;
    @Getter private Double prix;
    @Getter private String description;
    @Getter private AnnounceStatus status;
    public AnnounceUpdatedEvent(String id, String reference,Double prix,String description ,AnnounceStatus status)
        {
            super(id);
            this.reference=reference;
            this.prix=prix;
            this.description=description;
            this.status=status;
        }

}
