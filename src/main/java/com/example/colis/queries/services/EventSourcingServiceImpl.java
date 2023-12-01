package com.example.colis.queries.services;

import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;
@Service
public class EventSourcingServiceImpl implements EventSourcingService {
    private final EventStore eventStore;

    public EventSourcingServiceImpl(EventStore eventStore) {
        this.eventStore = eventStore;
    }
    @Override
    public DomainEventStream eventsByColisId(String colisId){
        DomainEventStream domainEventStream=eventStore.readEvents(colisId);
        return domainEventStream;
    }
    @Override
    public DomainEventStream  eventsByDemandeId(String demandeId){
        DomainEventStream domainEventStream=eventStore.readEvents(demandeId);
        return domainEventStream;
    }

}
