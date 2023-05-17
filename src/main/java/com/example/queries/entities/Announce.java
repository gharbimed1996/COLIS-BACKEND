package com.example.queries.entities;

import com.example.commonApi.enums.AnnounceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announce {
    @Id
    private String id;
    private String reference;
    private Double prix;
    private String description;


    @Enumerated(EnumType.STRING)
    private AnnounceStatus status;
    @OneToMany(mappedBy = "announce")
    private Collection<Operation> operations;

}
