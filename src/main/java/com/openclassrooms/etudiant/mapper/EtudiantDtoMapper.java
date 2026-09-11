package com.openclassrooms.etudiant.mapper;

import com.openclassrooms.etudiant.dto.EtudiantRequestDTO;
import com.openclassrooms.etudiant.entities.Etudiant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EtudiantDtoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "updated_at", ignore = true)
    Etudiant toEntity(EtudiantRequestDTO etudiantDTO);
}
