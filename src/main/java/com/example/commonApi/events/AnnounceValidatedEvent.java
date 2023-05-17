package com.example.commonApi.events;

import com.example.commonApi.enums.AnnounceStatus;
import lombok.Getter;

public class AnnounceValidatedEvent extends BaseEvent<String>{
    @Getter private AnnounceStatus status;
    public  AnnounceValidatedEvent (String id, AnnounceStatus status)
    {
        super(id);
        this.status=status;
    }
}
