package com.example.colis.events;

import com.example.colis.enums.ColisStatus;
import lombok.Getter;

public class ColisUpdatedEvent extends BaseEvent<String>{
    @Getter private String matricule;
    @Getter private String description;
    @Getter private boolean fragile;
    @Getter private ColisStatus status;

    public ColisUpdatedEvent(String id, String matricule, String description, boolean fragile, ColisStatus status) {
        super(id);
        this.matricule = matricule;
        this.description = description;
        this.fragile = fragile;
        this.status = status;
    }
}
