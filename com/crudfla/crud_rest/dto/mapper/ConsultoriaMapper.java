package com.crudfla.crud_rest.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crudfla.crud_rest.dto.ConsultoriaCreateDTO;
import com.crudfla.crud_rest.dto.ConsultoriaResponseDTO;
import com.crudfla.crud_rest.modelo.Consultoria;

@Component
public class ConsultoriaMapper {

    @Autowired
    private ModelMapper mapper;

    // Convert ConsultoriaCreateDTO to Consultoria entity
    public Consultoria toEntity(ConsultoriaCreateDTO consultoriaCreateDTO) {
        return mapper.map(consultoriaCreateDTO, Consultoria.class);
    }

    // Convert Consultoria entity to ConsultoriaResponseDTO
    public ConsultoriaResponseDTO toDTO(Consultoria consultoria) {
        return mapper.map(consultoria, ConsultoriaResponseDTO.class);
    }

    // Convert list of Consultoria entities to list of ConsultoriaResponseDTOs
    public List<ConsultoriaResponseDTO> toDTO(List<Consultoria> consultorias) {
        return consultorias.stream()
            .map(consultoria -> toDTO(consultoria))
            .collect(Collectors.toList());
    }
}
