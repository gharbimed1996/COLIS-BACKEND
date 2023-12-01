package com.example.colis.events;

import com.example.colis.enums.ColisStatus;
import lombok.Getter;

public class DemandeDeletedEvent {
    private String id;
    @Getter private ColisStatus status;

    public DemandeDeletedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
