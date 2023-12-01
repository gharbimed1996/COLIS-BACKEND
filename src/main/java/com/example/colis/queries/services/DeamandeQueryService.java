package com.example.colis.queries.services;

import com.example.colis.events.*;
import com.example.colis.queries.dtos.GetAllColisQueryDTO;
import com.example.colis.queries.dtos.GetAllDemandeQueryDTO;
import com.example.colis.queries.dtos.GetDemandeQueryDTO;
import com.example.colis.queries.entities.Colis;
import com.example.colis.queries.entities.Demande;
import com.example.colis.queries.repositories.DemandeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class DeamandeQueryService {
    private final DemandeRepository demandeRepository;

    private final QueryUpdateEmitter queryUpdateEmitter;

    @EventHandler
    public void on(DemandeCreatedEvent demandeCreatedEvent){
        log.info("***");
        log.info("DemandeCreatedEventRecieved");
        Demande demandes=new Demande();
        demandes.setDemandeId(demandeCreatedEvent.getId());
        demandes.setAdresse(demandeCreatedEvent.getAdresse());
        demandes.setNom(demandeCreatedEvent.getNom());
        demandes.setNumTel(demandeCreatedEvent.getNumTel());
        demandes.setEmail(demandeCreatedEvent.getEmail());
        demandes.setDescription(demandeCreatedEvent.getDescription());
        demandes.setStatus(demandeCreatedEvent.getStatus());
        demandeRepository.save(demandes);
    }
    @EventHandler
    public void on(DemandeUpdatedEvent event){
        log.info("***");
        log.info("ColisUpdatedEventRecieved");
          Demande demandes = demandeRepository.findById(event.getId()).get();
        demandes.setDemandeId(event.getId());
        demandes.setAdresse(event.getAdresse());
        demandes.setNom(event.getNom());
        demandes.setNumTel(event.getNumTel());
        demandes.setEmail(event.getEmail());
        demandes.setDescription(event.getDescription());
        demandes.setStatus(event.getStatus());
        demandeRepository.save(demandes);
    }
    @EventHandler
    public void on(DemandeAccepterEvent event){
        log.info("***");
        log.info("DemandeAccepterEventRecieved");
        Demande demandes = demandeRepository.findById(event.getId()).get();
        demandes.setDemandeId(event.getId());
        demandes.setAdresse(event.getAdresse());
        demandes.setNom(event.getNom());
        demandes.setNumTel(event.getNumTel());
        demandes.setEmail(event.getEmail());
        demandes.setDescription(event.getDescription());
        demandes.setStatus(event.getStatus());
        demandeRepository.save(demandes);
    }
    @EventHandler
    public void on(DemandeEnTraitementEvent event){
        log.info("***");
        log.info("DemandeEnTraitementEventRecieved");
        Demande demandes = demandeRepository.findById(event.getId()).get();
        demandes.setDemandeId(event.getId());
        demandes.setAdresse(event.getAdresse());
        demandes.setNom(event.getNom());
        demandes.setNumTel(event.getNumTel());
        demandes.setEmail(event.getEmail());
        demandes.setDescription(event.getDescription());
        demandes.setStatus(event.getStatus());
        demandeRepository.save(demandes);
    }
    @EventHandler
    public void on(DemandeRefuserEvent event){
        log.info("***");
        log.info("DemandeRefuserEventRecieved");
        Demande demandes = demandeRepository.findById(event.getId()).get();
        demandes.setDemandeId(event.getId());
        demandes.setAdresse(event.getAdresse());
        demandes.setNom(event.getNom());
        demandes.setNumTel(event.getNumTel());
        demandes.setEmail(event.getEmail());
        demandes.setDescription(event.getDescription());
        demandes.setStatus(event.getStatus());
        demandeRepository.save(demandes);
    }


    @QueryHandler
    public Demande on (GetDemandeQueryDTO query) {
        return demandeRepository.findById(query.getId()).get();
    }

    @QueryHandler
    public List<Demande> on(GetAllDemandeQueryDTO getAllColisQueryDTO) {
        return demandeRepository.findAll();
    }

    @EventHandler
    public void on(DemandeDeletedEvent event){
        log.info("************************");
        log.info("AnnounceDeletedEvent received");
        demandeRepository.deleteById(event.getId());
    }
}
