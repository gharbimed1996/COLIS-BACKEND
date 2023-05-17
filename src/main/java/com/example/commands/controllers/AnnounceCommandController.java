package com.example.commands.controllers;
import com.example.commonApi.commands.CreateAnnounceCommand;
import com.example.commonApi.commands.DeleteAnnounceCommand;
import com.example.commonApi.commands.UpdateAnnounceCommand;
import com.example.commonApi.dtos.UpdateAnnounceRequestDTO;
import com.example.commonApi.dtos.createAnnounceRequestDTO;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins ="http://localhost:4200/")
@RequestMapping(path = "/commands/announce")
public class AnnounceCommandController {
    private EventStore eventStore;
    private CommandGateway commandGateway;
    private final QueryGateway queryGateway;


    public AnnounceCommandController(CommandGateway commandGateway, EventStore eventStore,QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
        this.queryGateway = queryGateway;

    }
    @PostMapping(path = "/create")
    public  CompletableFuture<String> createAnnounce(@RequestBody createAnnounceRequestDTO request){
        CompletableFuture<String> commandResponse =commandGateway.send(new CreateAnnounceCommand(
        UUID.randomUUID().toString(),
        request.getReference(),
        request.getPrix(),
        request.getDescription()
               ));
    return commandResponse;
    }

    @PutMapping(path = "/update/{AnnounceId}")
    public CompletableFuture<String>updateAnnounce(@PathVariable(name = "AnnounceId", required = false) String id, @RequestBody UpdateAnnounceRequestDTO request) {

        CompletableFuture<String> commandResponse =commandGateway.send(new UpdateAnnounceCommand(
                id,
                request.getReference(),
                request.getPrix(),
                request.getDescription()
        ));
        return commandResponse.exceptionally(ex ->{
            throw new RuntimeException("Failed to update announce:"+ex.getMessage());
        });
    }
    /*
    @DeleteMapping(path = "/{AnnounceId}")
    public ResponseEntity<String> deleteAnnounce(@PathVariable String AnnounceId) {
        try {
            CompletableFuture<String> commandResponse = commandGateway.send(new DeleteAnnounceCommand(AnnounceId));
            return ResponseEntity.ok("Announce deleted successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete announce: " + e.getMessage());
        }
    }*/
    @DeleteMapping(path ="/{announceId}")
    public CompletableFuture<String> deleteAnnounce(@PathVariable String announceId) {
        return commandGateway.send(new DeleteAnnounceCommand(announceId));
    }
    @ExceptionHandler()
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR
        );
        return entity;
    }

    @GetMapping("/eventStore/{announceId}")
    public java.util.stream.Stream eventStore(@PathVariable String announceId){
        return eventStore.readEvents(announceId).asStream();
    }
}
