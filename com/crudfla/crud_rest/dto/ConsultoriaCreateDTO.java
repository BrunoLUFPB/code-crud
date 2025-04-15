package com.crudfla.crud_rest.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ConsultoriaCreateDTO {
	
	@NotBlank(message = "O tipo deve ser informado")
	@Size(min = 2, message = "O nome deve ter no mínimo 2 caracteres")
    private String tipoConsultoria;  

    private String descricao;
    
    @NotNull(message = "Duração obrigatória")
    @DecimalMin(value = "0.5", message = "Mínimo 0.5 horas (30 minutos)")
    @DecimalMax(value = "24.0", message = "Máximo 24 horas")
    private Double duracaoHoras;
    
	@DecimalMin(value = "0.0", inclusive = false, message = "O valor da compra deve ser maior que 0")
	@NotNull(message = "Precisamos saber o valor da compra atual")
    private Long valor;

}
