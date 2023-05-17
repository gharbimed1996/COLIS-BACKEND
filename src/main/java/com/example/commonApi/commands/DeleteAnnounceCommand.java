package com.example.commonApi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DeleteAnnounceCommand {
    @TargetAggregateIdentifier
    private String announceId;

        public DeleteAnnounceCommand(String AnnounceId) {
            this.announceId = AnnounceId;
        }

        public String getAnnounceId() {
            return announceId;
        }
    }


