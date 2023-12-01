package com.example.colis.queries.repositories;

import com.example.colis.queries.entities.Colis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColisRepository extends JpaRepository<Colis,String> {


}
