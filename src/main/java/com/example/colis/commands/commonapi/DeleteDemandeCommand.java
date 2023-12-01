package com.example.colis.commands.commonapi;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DeleteDemandeCommand {
    @TargetAggregateIdentifier
    private String demandeId;

        public DeleteDemandeCommand(String DemandeId) {
            this.demandeId = DemandeId;
        }

        public String getdemandeId() {
            return demandeId;
        }
    }


