package com.colis.paypal.commande.service;

import com.colis.paypal.commonApi.Dt.ExecutePaymentCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ExecutePaymentCommandHandler {

    private final CommandGateway commandGateway;

    @Autowired
    public ExecutePaymentCommandHandler(CommandGateway commandGateway) {

        this.commandGateway = commandGateway;
    }

    public CompletableFuture<Void> handle(ExecutePaymentCommand command) {
        return commandGateway.send(command);
    }
}