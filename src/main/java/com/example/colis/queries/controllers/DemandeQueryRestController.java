package com.example.colis.queries.controllers;

import com.example.colis.queries.dtos.GetAllColisQueryDTO;
import com.example.colis.queries.dtos.GetAllDemandeQueryDTO;
import com.example.colis.queries.dtos.GetColisQueryDTO;
import com.example.colis.queries.dtos.GetDemandeQueryDTO;
import com.example.colis.queries.entities.Colis;
import com.example.colis.queries.entities.Demande;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/query/Demande")
public class DemandeQueryRestController {
    private QueryGateway queryGateway;

    public DemandeQueryRestController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    @GetMapping(path="/Demande/{id}")
    public Demande getDemande(@PathVariable String id){
        return queryGateway.query(new GetDemandeQueryDTO(id),ResponseTypes.instanceOf(Demande.class)).join();
    }
    @GetMapping(path="/AllDemande")
    public List<Demande> getAll(){
        List<Demande> reponse=queryGateway.query(new GetAllDemandeQueryDTO(), ResponseTypes.multipleInstancesOf(Demande.class)).join();
        return reponse;
    }

}
