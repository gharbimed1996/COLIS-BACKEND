package com.example.colis.queries.controllers;

import com.example.colis.queries.services.EventSourcingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Stream;
@RestController
@RequestMapping(path="/eventSourcing")
public class ColisEventSourcingRestController {
    private final EventSourcingService eventSourcingService;

    public ColisEventSourcingRestController(EventSourcingService eventSourcingService) {
        this.eventSourcingService = eventSourcingService;
    }


    @GetMapping(path = "/colisEvents/{id}")
    public Stream eventsByCRAsId(@PathVariable String id){
        return eventSourcingService.eventsByColisId(id).asStream();
    }
    @GetMapping(path = "/demandeEvents/{id}")
    public Stream eventsByDeamndeId(@PathVariable String id){
        return eventSourcingService.eventsByDemandeId(id).asStream();
    }
}
