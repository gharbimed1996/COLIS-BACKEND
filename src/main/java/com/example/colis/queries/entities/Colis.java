package com.example.colis.queries.entities;

import com.example.colis.enums.ColisStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Colis {
    @Id
    private String colisId;
    private String matricule;
    private String description;
    private boolean fragile;
    @Enumerated(EnumType.STRING)
    private ColisStatus status;
}
