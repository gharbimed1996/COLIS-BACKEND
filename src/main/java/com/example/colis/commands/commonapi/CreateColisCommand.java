package com.example.colis.commands.commonapi;

import lombok.Getter;


public class CreateColisCommand extends BaseCommand<String> {

    @Getter private String matricule;
    @Getter private String description;
    @Getter private boolean fragile;

    public CreateColisCommand(String id, String matricule, String description, boolean fragile) {
        super(id);
        this.matricule = matricule;
        this.description = description;
        this.fragile = fragile;
    }
}
