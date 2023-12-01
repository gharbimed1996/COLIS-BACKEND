package com.example.colis.queries.entities;

import com.example.colis.enums.DemandeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Demande {
        @Id
        private String demandeId;
        private String adresse;
        private String nom;
        private Long numTel;
        private String email;
        private String description;
        @Enumerated(EnumType.STRING)
        private DemandeStatus status;
    }

