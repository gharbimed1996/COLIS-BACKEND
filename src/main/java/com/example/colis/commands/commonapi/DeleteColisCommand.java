package com.example.colis.commands.commonapi;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DeleteColisCommand {
    @TargetAggregateIdentifier
    private String colisId;

        public DeleteColisCommand(String ColisId) {
            this.colisId = ColisId;
        }

        public String getColisId() {
            return colisId;
        }
    }


