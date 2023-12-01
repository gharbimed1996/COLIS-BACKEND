package com.example.colis.commands.services;

import com.example.colis.commands.commonapi.*;
import com.example.colis.commands.dtos.ColisRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ColisCommandServiceImpl implements ColisCommandService {
@Autowired
    private CommandGateway commandGateway;
    private CreateColisCommand command;
    @Override
    public CompletableFuture<String> createColis(ColisRequestDTO colisRequestDTO) {
            return commandGateway.send(new CreateColisCommand(
                    UUID.randomUUID().toString(),
                    colisRequestDTO.getMatricule(),
                    colisRequestDTO.getDescription(),
                    colisRequestDTO.isFragile()
            ));
        }

@Override
    public CompletableFuture<String> updateColis(ColisRequestDTO colisRequestDTO){

        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateColisCommand(
                colisRequestDTO.getColisId(),
                colisRequestDTO.getMatricule(),
                colisRequestDTO.getDescription(),
                colisRequestDTO.isFragile()
    ));
        return commandResponse.exceptionally(ex -> {
            throw new RuntimeException("Failed to update Colis: " + ex.getMessage());
        });

    }

    @Override
    public CompletableFuture<String> EnDepotColis(ColisRequestDTO colisRequestDTO) {

       return commandGateway.send(new EnDepotColisCommand(
               colisRequestDTO.getColisId(),
               colisRequestDTO.getMatricule(),
               colisRequestDTO.getDescription(),
               colisRequestDTO.isFragile()
        ));
    }
    @Override
    public CompletableFuture<String> EnCoursColis(ColisRequestDTO colisRequestDTO) {

        return commandGateway.send(new EnCoursColisCommand(
                colisRequestDTO.getColisId(),
                colisRequestDTO.getMatricule(),
                colisRequestDTO.getDescription(),
                colisRequestDTO.isFragile()
        ));
    }
    @Override
    public CompletableFuture<String> RetourColis(ColisRequestDTO colisRequestDTO) {

        return commandGateway.send(new RetourColisCommand(
                colisRequestDTO.getColisId(),
                colisRequestDTO.getMatricule(),
                colisRequestDTO.getDescription(),
                colisRequestDTO.isFragile()
        ));
    }
    @Override
    public CompletableFuture<String> LivréColis(ColisRequestDTO colisRequestDTO) {

        return commandGateway.send(new LivréColisCommand(
                colisRequestDTO.getColisId(),
                colisRequestDTO.getMatricule(),
                colisRequestDTO.getDescription(),
                colisRequestDTO.isFragile()
        ));
    }
}
