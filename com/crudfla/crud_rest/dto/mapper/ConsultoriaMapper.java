package com.crudfla.crud_rest.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crudfla.crud_rest.dto.ClienteCreateDTO;
import com.crudfla.crud_rest.dto.ClienteResponseDTO;
import com.crudfla.crud_rest.dto.ConsultoriaCreateDTO;
import com.crudfla.crud_rest.dto.ConsultoriaResponseDTO;
import com.crudfla.crud_rest.modelo.Consultoria;


@Component

public class ConsultoriaMapper {
	
	@Autowired
	private ModelMapper mapper;

	public Consultoria toEntity(ConsultoriaCreateDTO dto) {
		Consultoria entity = mapper.map(dto, Consultoria.class);
		return entity;
	}
	
	public ConsultoriaResponseDTO toDTO(Consultoria entity) {
		ConsultoriaResponseDTO dto = mapper.map(entity, ConsultoriaResponseDTO.class);
		return dto;
	}
	
	public List<ConsultoriaResponseDTO> toDTO(List<Consultoria> consultorias){
		return consultorias.stream()
				.map(consultoria -> toDTO(consultoria))
				.collect(Collectors.toList());
	}

}







