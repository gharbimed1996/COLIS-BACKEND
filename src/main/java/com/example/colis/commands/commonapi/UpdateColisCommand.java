package com.example.colis.commands.commonapi;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;

public class UpdateColisCommand {
    @TargetAggregateIdentifier
    @Getter private String colisId;
    @Getter private String matricule;
    @Getter private String description;
    @Getter private boolean fragile;

    public UpdateColisCommand(String colisId, String matricule, String description, boolean fragile) {
        this.colisId = colisId;
        this.matricule = matricule;
        this.description = description;
        this.fragile = fragile;
    }
}
