package com.example.colis.commands.controllers;

import com.example.colis.commands.commonapi.DeleteColisCommand;
import com.example.colis.commands.dtos.ColisRequestDTO;
import com.example.colis.commands.exceptions.FalseDateException;
import com.example.colis.commands.services.ColisCommandService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/commands/Colis")
public class ColisCommandContoller {
    private ColisCommandService colisCommandService;
    private EventStore eventStore;
    private CommandGateway commandGateway;

    public ColisCommandContoller(ColisCommandService colisCommandService, EventStore eventStore , CommandGateway commandGateway) {
        this.colisCommandService = colisCommandService;
        this.eventStore = eventStore;
        this.commandGateway = commandGateway;
    }

    @PostMapping(path = "/create")
    public CompletableFuture<String> createCRAs(@RequestBody ColisRequestDTO colisRequestDTO){
        return colisCommandService.createColis(colisRequestDTO);
    }
    @PutMapping(path = "/update")
    public CompletableFuture<String> UpdateCRAs(@RequestBody ColisRequestDTO request){
        return colisCommandService.updateColis(request);
    }
    @GetMapping("/eventStore/{colisId}")
    public Stream eventStore(@PathVariable String colisId){
        return (Stream)eventStore.readEvents(colisId).asStream() ;
    }


    @PutMapping(path = "/EnDepot")
    public CompletableFuture<String> EnDepotColis(@RequestBody ColisRequestDTO colisRequestDTO) {
        return colisCommandService.EnDepotColis(colisRequestDTO);
    }
    @PutMapping(path ="/EnCours")
    public CompletableFuture<String> EnCoursColis(@RequestBody ColisRequestDTO colisRequestDTO) {
        return colisCommandService.EnCoursColis(colisRequestDTO);
    }
    @PutMapping(path ="/Livré")
    public CompletableFuture<String> LivréColis(@RequestBody ColisRequestDTO colisRequestDTO) {
        return colisCommandService.LivréColis(colisRequestDTO);
    }
    @PutMapping(path ="/Retour")
    public CompletableFuture<String> RetourColis(@RequestBody ColisRequestDTO colisRequestDTO) {
        return colisCommandService.RetourColis(colisRequestDTO);
    }
    @ExceptionHandler(FalseDateException.class)
    public ResponseEntity<String> exceptionHandler(FalseDateException exception){
        return new ResponseEntity<String>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(path = "/{colisId}")
    public ResponseEntity<String> deleteColis(@PathVariable String colisId) {
        try {
            CompletableFuture<String> commandResponse = commandGateway.send(new DeleteColisCommand(colisId));
            // Wait for the command to be executed and get the result
            String result = commandResponse.get();
            return ResponseEntity.ok("Colis deleted successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete Colis: " + e.getMessage());
        }
    }

}
