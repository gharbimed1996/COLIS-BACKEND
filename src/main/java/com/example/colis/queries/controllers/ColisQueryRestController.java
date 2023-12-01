package com.example.colis.queries.controllers;

import com.example.colis.queries.dtos.GetAllColisQueryDTO;
import com.example.colis.queries.dtos.GetColisQueryDTO;
import com.example.colis.queries.entities.Colis;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/query")
public class ColisQueryRestController {
    private QueryGateway queryGateway;

    public ColisQueryRestController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    @GetMapping(path="/Colis/{id}")
    public Colis getColis(@PathVariable String id){
        return queryGateway.query(new GetColisQueryDTO(id),ResponseTypes.instanceOf(Colis.class)).join();
    }
    @GetMapping(path="/AllColis")
    public List<Colis> getAll(){
        List<Colis> reponse=queryGateway.query(new GetAllColisQueryDTO(), ResponseTypes.multipleInstancesOf(Colis.class)).join();
        return reponse;
    }

}
