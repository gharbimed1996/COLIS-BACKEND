package com.example.queries.contollers;

import com.example.commonApi.query.GetAllAnnouncesQuery;
import com.example.commonApi.query.GetAnnounceByIdQuery;
import com.example.queries.entities.Announce;
import com.example.queries.services.AnnounceServiceHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:4200/")
@RequestMapping(path = "/query/announces")
public class AnnounceQueryController {
    private QueryGateway queryGateway;
   // @Autowired
    //private final AnnounceServiceHandler announceServiceHandler;
    @GetMapping("/GetAllAnnounces")
    public List<Announce> announceList() {
        List<Announce> reponse=    queryGateway.query(new GetAllAnnouncesQuery(), ResponseTypes.multipleInstancesOf(Announce.class)).join();
        return reponse;
    }
    @GetMapping("/GetAnnounceById/{id}")
    public Announce getAnnounce(@PathVariable String id){
        return  queryGateway.query(new GetAnnounceByIdQuery(id),ResponseTypes.instanceOf(Announce.class)).join();

    }
   /* @GetMapping("/search")
    public List<Announce> search(@RequestParam("q") String query) {

        return announceServiceHandler.search(query);
    }*/

}
