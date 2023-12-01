package com.example.colis.commands.aggregates;


import com.example.colis.commands.commonapi.*;
import com.example.colis.enums.ColisStatus;
import com.example.colis.events.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import java.time.LocalDate;

@Aggregate
@Slf4j
public class ColisAggregate {


    @AggregateIdentifier
    private String colisId;
    private String matricule;
    private String description;
    private boolean fragile;
    private ColisStatus status;

public ColisAggregate() {

}

    @CommandHandler
    public ColisAggregate(CreateColisCommand createColisCommand) {
        log.info("CreateColisCommand Reveived");
        AggregateLifecycle.apply(
                new ColisCreatedEvent(
                        createColisCommand.getId(),
                        createColisCommand.getMatricule(),
                        createColisCommand.getDescription(),
                        createColisCommand.isFragile(),
                        ColisStatus.En_Atente));

    }
        @EventSourcingHandler
        public void on(ColisCreatedEvent event){
            log.info("ColisCreatedEvent Occured");
            this.colisId= event.getId();
            this.matricule=event.getMatricule();
            this.description=event.getDescription();
            this.fragile =event.isFragile();
            this.status=event.getStatus();
        }

    @CommandHandler
    public void on(EnDepotColisCommand command){
        AggregateLifecycle.apply(new ColisEnDepotEvent(
                command.getId(),
                command.getMatricule(),
                command.getDescription(),
                command.isFragile(),
                ColisStatus.EN_Depot
        ));
    }
    @EventSourcingHandler
    public void on(ColisEnDepotEvent event){
        this.colisId= event.getId();
        this.matricule=event.getMatricule();
        this.description=event.getDescription();
        this.fragile =event.isFragile();
        this.status=event.getStatus();
    }
    @CommandHandler
    public void on(LivréColisCommand command){
        AggregateLifecycle.apply(new ColisEnDepotEvent(
                command.getId(),
                command.getMatricule(),
                command.getDescription(),
                command.isFragile(),
                ColisStatus.Livré
        ));
    }
    @EventSourcingHandler
    public void on(ColisLivréEvent event){
        this.colisId= event.getId();
        this.matricule=event.getMatricule();
        this.description=event.getDescription();
        this.fragile =event.isFragile();
        this.status=event.getStatus();
    }
    @CommandHandler
    public void on(EnCoursColisCommand command){
        AggregateLifecycle.apply(new ColisEnDepotEvent(
                command.getId(),
                command.getMatricule(),
                command.getDescription(),
                command.isFragile(),
                ColisStatus.EN_Cours
        ));
    }
    @EventSourcingHandler
    public void on(ColisEnCoursEvent event){
        this.colisId= event.getId();
        this.matricule=event.getMatricule();
        this.description=event.getDescription();
        this.fragile =event.isFragile();
        this.status=event.getStatus();
    }
    @CommandHandler
    public void on(RetourColisCommand command){
        AggregateLifecycle.apply(new ColisEnDepotEvent(
                command.getId(),
                command.getMatricule(),
                command.getDescription(),
                command.isFragile(),
                ColisStatus.Retour
        ));
    }
    @EventSourcingHandler
    public void on(ColisRetourEvent event){
        this.colisId= event.getId();
        this.matricule=event.getMatricule();
        this.description=event.getDescription();
        this.fragile =event.isFragile();
        this.status=event.getStatus();
    }
    @CommandHandler
    public void handle(DeleteColisCommand command) {
        AggregateLifecycle.apply(new ColisDeletedEvent(command.getColisId()));
    }

    @EventSourcingHandler
    public void on(ColisDeletedEvent event) {
        this.status = ColisStatus.DELETED;
    }


}
