package com.example.queries.services;

import com.example.commonApi.events.AnnounceCreatedEvent;
import com.example.commonApi.events.AnnounceUpdatedEvent;
import com.example.commonApi.events.AnnounceValidatedEvent;
import com.example.commonApi.query.GetAllAnnouncesQuery;
import com.example.commonApi.query.GetAnnounceByIdQuery;
import com.example.queries.entities.Announce;
import com.example.queries.repositories.AnnounceRepository;
import com.example.queries.repositories.OperationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Transactional
@Service
public class AnnounceServiceHandler {
    private AnnounceRepository announceRepository;
    @EventHandler
    public void on(AnnounceCreatedEvent event){
        log.info("************************");
        log.info("AnnounceCreatedEvent received");
        Announce announce = new Announce();
        announce.setId(event.getId());
        announce.setReference(event.getReference());
        announce.setPrix(event.getPrix());
        announce.setDescription(event.getDescription());
        announce.setStatus(event.getStatus());

        announceRepository.save(announce);
    }
    @EventHandler
    public void on(AnnounceUpdatedEvent event){
        log.info("************************");
        log.info("AnnounceUpdatedEvent received");
        Announce announce = new Announce();
        announce.setId(event.getId());
        announce.setReference(event.getReference());
        announce.setPrix(event.getPrix());
        announce.setDescription(event.getDescription());
        announce.setStatus(event.getStatus());

        announceRepository.save(announce);
    }
    @EventHandler
    public void on(AnnounceValidatedEvent event){
        log.info("************************");
        log.info("ValidationEvent received");
        Announce announce = announceRepository.findById(event.getId()).get();
        announce.setId(event.getId());
        announce.setStatus(event.getStatus());
        announceRepository.save(announce);
    }
    @QueryHandler
    public List<Announce> on (GetAllAnnouncesQuery query) {
        return announceRepository.findAll();
    }
    @QueryHandler
    public Announce on (GetAnnounceByIdQuery query) {
        return announceRepository.findById(query.getId()).get();
    }
   /* @QueryHandler
    public List<Announce> search(String query) {
        return announceRepository.findByNomContainingIgnoreCase(query);
    }
*/
}
