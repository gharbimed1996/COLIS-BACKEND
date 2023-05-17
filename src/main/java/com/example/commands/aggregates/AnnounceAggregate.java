package com.example.commands.aggregates;

import com.example.commonApi.commands.CreateAnnounceCommand;
import com.example.commonApi.commands.DeleteAnnounceCommand;
import com.example.commonApi.commands.UpdateAnnounceCommand;
import com.example.commonApi.enums.AnnounceStatus;
import com.example.commonApi.events.AnnounceCreatedEvent;
import com.example.commonApi.events.AnnounceDeletedEvent;
import com.example.commonApi.events.AnnounceUpdatedEvent;
import com.example.commonApi.events.AnnounceValidatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import java.util.Date;

@Aggregate
public class AnnounceAggregate {
    @AggregateIdentifier
    private String AnnounceId;
    private String reference;
    private Double prix;
    private String description;
    private AnnounceStatus status;
    public AnnounceAggregate() {
        //Required by Axon
    }
    @CommandHandler
    public AnnounceAggregate(CreateAnnounceCommand createAnnounceCommand) {
        //Required by Axon
        AggregateLifecycle.apply(new AnnounceCreatedEvent(
                createAnnounceCommand.getId(),
                createAnnounceCommand.getReference(),
                createAnnounceCommand.getPrix(),
                createAnnounceCommand.getDescription(),
                AnnounceStatus.CREATED                ));
    }
    @EventSourcingHandler
    public void on(AnnounceCreatedEvent event)
    {
    this.AnnounceId=event.getId();
    this.reference= event.getReference();
    this.prix=event.getPrix();
    this.description=event.getDescription();
    this.status= AnnounceStatus.CREATED;
    AggregateLifecycle.apply(new AnnounceValidatedEvent(
            event.getId(),
            AnnounceStatus.VALIDATED
    ));
    }
    @EventSourcingHandler
    public void on(AnnounceValidatedEvent event){
        this.status=event.getStatus();
    }
    @CommandHandler
    public void handle(UpdateAnnounceCommand command) {
        AggregateLifecycle.apply(new AnnounceUpdatedEvent(
                command.getId(),
                command.getReference(),
                command.getPrix(),
                command.getDescription(),
                AnnounceStatus.UPDATED
        ));

}
    @EventSourcingHandler
    public void on(AnnounceUpdatedEvent event){
    this.reference= event.getReference();
    this.prix=event.getPrix();
    this.description=event.getDescription();
    this.status= AnnounceStatus.UPDATED;
    AggregateLifecycle.apply(new AnnounceValidatedEvent(
            event.getId(),
            AnnounceStatus.VALIDATED
    ));
    }


    @CommandHandler
    public void handle(DeleteAnnounceCommand command) {
        AggregateLifecycle.apply(new AnnounceDeletedEvent(command.getAnnounceId()));
    }

    @EventSourcingHandler
    public void on(AnnounceDeletedEvent event) {
        this.status = AnnounceStatus.DELETED;
    }



}
