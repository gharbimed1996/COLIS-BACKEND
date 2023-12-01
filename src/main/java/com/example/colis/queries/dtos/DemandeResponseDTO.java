package com.example.colis.queries.dtos;

import com.example.colis.enums.DemandeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeResponseDTO {
    private String demandeId;
    private String adresse;
    private String nom;
    private Long numTel;
    private String email;
    private String description;
    private DemandeStatus status;
}
