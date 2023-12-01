package com.example.colis.queries.services;

import org.axonframework.eventsourcing.eventstore.DomainEventStream;


public interface EventSourcingService {
    DomainEventStream eventsByColisId(String colisId);
    DomainEventStream eventsByDemandeId(String demandeId);
}
