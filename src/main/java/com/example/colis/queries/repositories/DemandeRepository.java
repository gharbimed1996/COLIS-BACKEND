package com.example.colis.queries.repositories;


import com.example.colis.queries.entities.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository extends JpaRepository<Demande,String> {
}
