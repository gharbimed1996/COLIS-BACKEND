package com.example.colis.queries.services;

import com.example.colis.events.*;
import com.example.colis.mappers.ColisMapper;
import com.example.colis.queries.dtos.GetAllColisQueryDTO;
import com.example.colis.queries.dtos.GetColisQueryDTO;
import com.example.colis.queries.entities.Colis;
import com.example.colis.queries.repositories.ColisRepository;
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
public class ColisQueryService {
    private final ColisRepository colisRepository;

    private final QueryUpdateEmitter queryUpdateEmitter;

    @EventHandler
    public void on(ColisCreatedEvent colisCreatedEvent){
        log.info("***");
        log.info("ColisCreatedEventRecieved");
        Colis colis=new Colis();
        colis.setColisId(colisCreatedEvent.getId());
        colis.setMatricule(colisCreatedEvent.getMatricule());
        colis.setDescription(colisCreatedEvent.getDescription());
        colis.setFragile(colisCreatedEvent.isFragile());
        colis.setStatus(colisCreatedEvent.getStatus());
        colisRepository.save(colis);
    }
    @EventHandler
    public void on(ColisUpdatedEvent event){
        log.info("***");
        log.info("ColisUpdatedEventRecieved");
          Colis colis = colisRepository.findById(event.getId()).get();
        colis.setMatricule(event.getMatricule());
        colis.setDescription(event.getDescription());
        colis.setFragile(event.isFragile());
        colis.setStatus(event.getStatus());
          colisRepository.save(colis);
    }
    @EventHandler
    public void on(ColisEnCoursEvent event){
        log.info("***");
        log.info("ColisEnCoursEventRecieved");
        Colis colis = colisRepository.findById(event.getId()).get();
        colis.setMatricule(event.getMatricule());
        colis.setDescription(event.getDescription());
        colis.setFragile(event.isFragile());
        colis.setStatus(event.getStatus());
        colisRepository.save(colis);
    }
    @EventHandler
    public void on(ColisEnDepotEvent event){
        log.info("***");
        log.info("ColisEnDepotEventRecieved");
        Colis colis = colisRepository.findById(event.getId()).get();
        colis.setMatricule(event.getMatricule());
        colis.setDescription(event.getDescription());
        colis.setFragile(event.isFragile());
        colis.setStatus(event.getStatus());
        colisRepository.save(colis);
    }
    @EventHandler
    public void on(ColisLivréEvent event){
        log.info("***");
        log.info("ColisLivréEventRecieved");
        Colis colis = colisRepository.findById(event.getId()).get();
        colis.setMatricule(event.getMatricule());
        colis.setDescription(event.getDescription());
        colis.setFragile(event.isFragile());
        colis.setStatus(event.getStatus());
        colisRepository.save(colis);
    }
    @EventHandler
    public void on(ColisRetourEvent event){
        log.info("***");
        log.info("ColisRetourEventRecieved");
        Colis colis = colisRepository.findById(event.getId()).get();
        colis.setMatricule(event.getMatricule());
        colis.setDescription(event.getDescription());
        colis.setFragile(event.isFragile());
        colis.setStatus(event.getStatus());
        colisRepository.save(colis);
    }

    @QueryHandler
    public Colis on (GetColisQueryDTO query) {
        return colisRepository.findById(query.getId()).get();
    }

    @QueryHandler
    public List<Colis> on(GetAllColisQueryDTO getAllCRAsRequestDTO) {
        return colisRepository.findAll();
    }

    @EventHandler
    public void on(ColisDeletedEvent event){
        log.info("************************");
        log.info("AnnounceDeletedEvent received");
        colisRepository.deleteById(event.getId());
    }
}
