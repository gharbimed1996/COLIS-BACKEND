package com.example.colis.queries.dtos;

import com.example.colis.enums.ColisStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColisResponseDTO {
    private String colisId;
    private String matricule;
    private String description;
    private boolean fragile;
    private ColisStatus status;
}
