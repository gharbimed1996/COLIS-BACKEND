package com.example.commonApi.commands;

import lombok.Getter;
import java.util.Date;

public class CreateAnnounceCommand extends baseCommand<String> {
    @Getter private String reference;
    @Getter private Double prix;
    @Getter private String description;
    public CreateAnnounceCommand(String id, String reference, Double prix, String description)
    {
        super(id);
        this.reference=reference;
        this.prix=prix;
        this.description=description;

    }
}
