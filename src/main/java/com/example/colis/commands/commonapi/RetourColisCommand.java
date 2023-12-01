package com.example.colis.commands.commonapi;

import lombok.Getter;

import java.time.LocalDate;

public class RetourColisCommand extends BaseCommand<String>{

    @Getter private String matricule;
    @Getter private String description;
    @Getter private boolean fragile;

    public RetourColisCommand(String id, String matricule, String description, boolean fragile) {
        super(id);
        this.matricule = matricule;
        this.description = description;
        this.fragile = fragile;
    }
}
