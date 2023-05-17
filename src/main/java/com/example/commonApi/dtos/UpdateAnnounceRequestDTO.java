package com.example.commonApi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAnnounceRequestDTO {
    private String reference;
    private Double prix;
    private String description;

}
