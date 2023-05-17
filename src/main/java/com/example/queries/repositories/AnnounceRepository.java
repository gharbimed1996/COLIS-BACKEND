package com.example.queries.repositories;

import com.example.queries.entities.Announce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AnnounceRepository extends JpaRepository<Announce,String>{
    //List<Announce> findByNomContainingIgnoreCase(String query);
}