package com.example.commonApi.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class createAnnounceRequestDTO {
    private String reference;
    private Double prix;
    private String description;

}
