package com.example.colis.commands.controllers;


import com.example.colis.commands.commonapi.DeleteColisCommand;
import com.example.colis.commands.commonapi.DeleteDemandeCommand;
import com.example.colis.commands.dtos.DemandeRequestDTO;
import com.example.colis.commands.exceptions.FalseDateException;
import com.example.colis.commands.services.DemandeCommandService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/commands/Demande")
public class DemandeCommandContoller {
    private DemandeCommandService demandeCommandService;
    private EventStore eventStore;
    private CommandGateway commandGateway;

    public DemandeCommandContoller(DemandeCommandService demandeCommandService, EventStore eventStore , CommandGateway commandGateway) {
        this.demandeCommandService = demandeCommandService;
        this.eventStore = eventStore;
        this.commandGateway = commandGateway;
    }

    @PostMapping(path = "/create")
    public CompletableFuture<String> createCRAs(@RequestBody DemandeRequestDTO demandeRequestDTO){
        return demandeCommandService.createDemande(demandeRequestDTO);
    }
    @PutMapping(path = "/update")
    public CompletableFuture<String> UpdateCRAs(@RequestBody DemandeRequestDTO demandeRequestDTO){
        return demandeCommandService.updateDemande(demandeRequestDTO);
    }
    @GetMapping("/eventStore/{demandeId}")
    public Stream eventStore(@PathVariable String demandeId){
        return (Stream)eventStore.readEvents(demandeId).asStream() ;
    }


    @PutMapping(path = "/Accepter")
    public CompletableFuture<String> AccepterDemande(@RequestBody DemandeRequestDTO demandeRequestDTO) {
        return demandeCommandService.accepterDemande(demandeRequestDTO);
    }
    @PutMapping(path ="/Refuser")
    public CompletableFuture<String> Refuseremande(@RequestBody DemandeRequestDTO demandeRequestDTO) {
        return demandeCommandService.refuserDemande(demandeRequestDTO);
    }
    @PutMapping(path ="/EnTraitement")
    public CompletableFuture<String> LivréColis(@RequestBody DemandeRequestDTO demandeRequestDTO) {
        return demandeCommandService.enTraitementDemande(demandeRequestDTO);
    }
    @ExceptionHandler(FalseDateException.class)
    public ResponseEntity<String> exceptionHandler(FalseDateException exception){
        return new ResponseEntity<String>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(path = "/{demandeId}")
    public ResponseEntity<String> deleteDemande(@PathVariable String demandeId) {
        try {
            CompletableFuture<String> commandResponse = commandGateway.send(new DeleteDemandeCommand(demandeId));
            // Wait for the command to be executed and get the result
            String result = commandResponse.get();
            return ResponseEntity.ok("demande deleted successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete demande: " + e.getMessage());
        }
    }

}
