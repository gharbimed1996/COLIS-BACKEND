package com.example.colis.commands.commonapi;

import lombok.Getter;

import java.time.LocalDate;

public class EnDepotColisCommand extends BaseCommand<String>{

    @Getter private String matricule;
    @Getter private String description;
    @Getter private boolean fragile;

    public EnDepotColisCommand(String id, String matricule, String description, boolean fragile) {
        super(id);
        this.matricule = matricule;
        this.description = description;
        this.fragile = fragile;
    }
}
