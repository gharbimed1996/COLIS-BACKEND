package com.colis.paypal.commande.controllers;


import com.colis.paypal.commonApi.commands.CreateAdCommand;
import com.colis.paypal.commonApi.Dt.createAdRequestDTO;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;

import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:4200/")
@RestController
@RequestMapping(path = "/commands/ad")
public class AdController {
    private EventStore eventStore;
    private CommandGateway commandGateway;


    @PostMapping(path = "/create")
    public CompletableFuture<String> createAnnounce(@RequestBody createAdRequestDTO request) {
        CompletableFuture<String> commandResponse = commandGateway.send(new CreateAdCommand(
                UUID.randomUUID().toString(),
                request.getPrix(),
                request.getNom(),
                request.getPrenom(),
                request.getEmail(),
                request.getTelephone()
        ));
        return commandResponse;
    }
    @GetMapping("/eventStore/{ad}")
    public java.util.stream.Stream eventStore(@PathVariable String adId){
        return eventStore.readEvents(adId).asStream();
    }
}
