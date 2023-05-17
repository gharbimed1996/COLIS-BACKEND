package com.example.commonApi.events;

import com.example.commonApi.enums.AnnounceStatus;
import lombok.Getter;

public class AnnounceDeletedEvent {
    private String id;
    @Getter private AnnounceStatus status;

    public AnnounceDeletedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
