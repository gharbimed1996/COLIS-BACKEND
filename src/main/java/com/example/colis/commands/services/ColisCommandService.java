package com.example.colis.commands.services;

import com.example.colis.commands.dtos.ColisRequestDTO;

import java.util.concurrent.CompletableFuture;

public interface ColisCommandService {
    CompletableFuture<String> createColis(ColisRequestDTO colisRequestDTO);
    CompletableFuture<String> updateColis(ColisRequestDTO colisRequestDTO);
    CompletableFuture<String> EnDepotColis(ColisRequestDTO colisRequestDTO);
    CompletableFuture<String> EnCoursColis(ColisRequestDTO colisRequestDTO);
    CompletableFuture<String> RetourColis(ColisRequestDTO colisRequestDTO);
    CompletableFuture<String> LivréColis(ColisRequestDTO colisRequestDTO);

}
