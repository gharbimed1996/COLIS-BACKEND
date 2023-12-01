package com.example.colis.commands.aggregates;


import com.example.colis.commands.commonapi.*;
import com.example.colis.enums.ColisStatus;
import com.example.colis.enums.DemandeStatus;
import com.example.colis.events.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Aggregate
@Slf4j
public class DemandeAggregate {
    @AggregateIdentifier
    private String demandeId;
    private String adresse;
    private String nom;
    private Long numTel;
    private String email;
    private String description;
    private DemandeStatus status;

public DemandeAggregate() {

}

    @CommandHandler
    public DemandeAggregate(CreateDemandeCommand createDemandeCommand) {
        log.info("CreateDemandeCommand Reveived");
        AggregateLifecycle.apply(
                new DemandeCreatedEvent(
                        createDemandeCommand.getId(),
                        createDemandeCommand.getAdresse(),
                        createDemandeCommand.getNom(),
                        createDemandeCommand.getNumTel(),
                        createDemandeCommand.getEmail(),
                        createDemandeCommand.getDescription(),
                        DemandeStatus.EN_TRAITEMENT));

    }
        @EventSourcingHandler
        public void on(DemandeCreatedEvent event){
            log.info("ColisCreatedEvent Occured");
            this.demandeId= event.getId();
            this.adresse=event.getAdresse();
            this.nom=event.getNom();
            this.numTel =event.getNumTel();
            this.email=event.getEmail();
            this.description =event.getDescription();
            this.status=event.getStatus();
        }
    @CommandHandler
    public void on(UpdateDemandeCommand command){
        AggregateLifecycle.apply(new DemandeAccepterEvent(
                command.getDemandeId(),
                command.getAdresse(),
                command.getNom(),
                command.getNumTel(),
                command.getEmail(),
                command.getDescription(),
                DemandeStatus.EN_TRAITEMENT));

    }
    @EventSourcingHandler
    public void on(DemandeUpdatedEvent event){
        this.demandeId= event.getId();
        this.adresse=event.getAdresse();
        this.nom=event.getNom();
        this.numTel =event.getNumTel();
        this.email=event.getEmail();
        this.description =event.getDescription();
        this.status=event.getStatus();
    }

    @CommandHandler
    public void on(AccepterDemandeCommand command){
        AggregateLifecycle.apply(new DemandeAccepterEvent(
                command.getId(),
                command.getAdresse(),
                command.getNom(),
                command.getNumTel(),
                command.getEmail(),
                command.getDescription(),
                DemandeStatus.ACCEPTER));

    }
    @EventSourcingHandler
    public void on(DemandeAccepterEvent event){
        this.demandeId= event.getId();
        this.adresse=event.getAdresse();
        this.nom=event.getNom();
        this.numTel =event.getNumTel();
        this.email=event.getEmail();
        this.description =event.getDescription();
        this.status=event.getStatus();
    }
    @CommandHandler
    public void on(RefuserDemandeCommand command){
        AggregateLifecycle.apply(new DemandeAccepterEvent(
                command.getId(),
                command.getAdresse(),
                command.getNom(),
                command.getNumTel(),
                command.getEmail(),
                command.getDescription(),
                DemandeStatus.REFUSER));

    }
    @EventSourcingHandler
    public void on(DemandeRefuserEvent event){
        this.demandeId= event.getId();
        this.adresse=event.getAdresse();
        this.nom=event.getNom();
        this.numTel =event.getNumTel();
        this.email=event.getEmail();
        this.description =event.getDescription();
        this.status=event.getStatus();
    }

    @CommandHandler
    public void handle(DeleteDemandeCommand command) {
        AggregateLifecycle.apply(new DemandeDeletedEvent(command.getdemandeId()));
    }

    @EventSourcingHandler
    public void on(ColisDeletedEvent event) {
        this.status = DemandeStatus.DELETED;
    }

}
