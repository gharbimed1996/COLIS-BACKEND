package com.example.colis.commands.services;

import com.example.colis.commands.dtos.ColisRequestDTO;
import com.example.colis.commands.dtos.DemandeRequestDTO;

import java.util.concurrent.CompletableFuture;

public interface DemandeCommandService {
    CompletableFuture<String> createDemande(DemandeRequestDTO demandeRequestDTO);
    CompletableFuture<String> updateDemande(DemandeRequestDTO demandeRequestDTO);
    CompletableFuture<String> refuserDemande(DemandeRequestDTO demandeRequestDTO);
    CompletableFuture<String> enTraitementDemande(DemandeRequestDTO demandeRequestDTO);
    CompletableFuture<String> accepterDemande(DemandeRequestDTO demandeRequestDTO);

}
