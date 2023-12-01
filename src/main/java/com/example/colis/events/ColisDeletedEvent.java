package com.example.colis.events;

import com.example.colis.enums.ColisStatus;

import lombok.Getter;

public class ColisDeletedEvent {
    private String id;
    @Getter private ColisStatus status;

    public ColisDeletedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
