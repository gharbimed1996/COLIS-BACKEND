package com.example.colis.commands.services;

import com.example.colis.commands.commonapi.*;
import com.example.colis.commands.dtos.ColisRequestDTO;
import com.example.colis.commands.dtos.DemandeRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class DemandeCommandServiceImpl implements DemandeCommandService {
@Autowired
    private CommandGateway commandGateway;
    private CreateDemandeCommand command;
    @Override
    public CompletableFuture<String> createDemande(DemandeRequestDTO demandeRequestDTO) {
            return commandGateway.send(new CreateDemandeCommand(
                    UUID.randomUUID().toString(),
                    demandeRequestDTO.getAdresse(),
                    demandeRequestDTO.getNom(),
                    demandeRequestDTO.getNumTel(),
                    demandeRequestDTO.getEmail(),
                    demandeRequestDTO.getDescription()
            ));
        }

@Override
    public CompletableFuture<String> updateDemande(DemandeRequestDTO demandeRequestDTO){

        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateDemandeCommand(
                demandeRequestDTO.getDemandeId(),
                demandeRequestDTO.getAdresse(),
                demandeRequestDTO.getNom(),
                demandeRequestDTO.getNumTel(),
                demandeRequestDTO.getEmail(),
                demandeRequestDTO.getDescription()
    ));
        return commandResponse.exceptionally(ex -> {
            throw new RuntimeException("Failed to update Colis: " + ex.getMessage());
        });

    }

    @Override
    public CompletableFuture<String> refuserDemande(DemandeRequestDTO demandeRequestDTO) {

       return commandGateway.send(new RefuserDemandeCommand(
               demandeRequestDTO.getDemandeId(),
               demandeRequestDTO.getAdresse(),
               demandeRequestDTO.getNom(),
               demandeRequestDTO.getNumTel(),
               demandeRequestDTO.getEmail(),
               demandeRequestDTO.getDescription()
        ));
    }
    @Override
    public CompletableFuture<String> enTraitementDemande(DemandeRequestDTO demandeRequestDTO) {

        return commandGateway.send(new RefuserDemandeCommand(
                demandeRequestDTO.getDemandeId(),
                demandeRequestDTO.getAdresse(),
                demandeRequestDTO.getNom(),
                demandeRequestDTO.getNumTel(),
                demandeRequestDTO.getEmail(),
                demandeRequestDTO.getDescription()
        ));
    }
    @Override
    public CompletableFuture<String> accepterDemande(DemandeRequestDTO demandeRequestDTO) {

        return commandGateway.send(new AccepterDemandeCommand(
                demandeRequestDTO.getDemandeId(),
                demandeRequestDTO.getAdresse(),
                demandeRequestDTO.getNom(),
                demandeRequestDTO.getNumTel(),
                demandeRequestDTO.getEmail(),
                demandeRequestDTO.getDescription()
        ));
    }

}
