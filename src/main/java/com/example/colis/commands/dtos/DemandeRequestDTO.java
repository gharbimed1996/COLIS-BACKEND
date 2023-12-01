package com.example.colis.commands.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class DemandeRequestDTO {
     private String demandeId;
     private String adresse;
     private String nom;
     private Long numTel;
     private String email;
     private String description;
}
