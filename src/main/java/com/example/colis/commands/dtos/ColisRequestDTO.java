package com.example.colis.commands.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @AllArgsConstructor @NoArgsConstructor
public class ColisRequestDTO {
     private String colisId;
     private String matricule;
     private String description;
     private boolean fragile;
}
