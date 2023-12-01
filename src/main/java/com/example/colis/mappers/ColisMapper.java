package com.example.colis.mappers;


import com.example.colis.queries.dtos.ColisResponseDTO;
import com.example.colis.queries.entities.Colis;
import org.mapstruct.Mapper;
@Mapper(componentModel="spring")
public interface ColisMapper {
    ColisResponseDTO ColisToColisDTO(Colis colis);
}
