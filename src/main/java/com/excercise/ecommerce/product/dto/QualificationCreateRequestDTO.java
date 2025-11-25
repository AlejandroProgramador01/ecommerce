package com.excercise.ecommerce.product.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QualificationCreateRequestDTO {

    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value = 5, message = "La calificación máxima es 5")
    private int rating;

    @Size(max = 500, message = "El comentario no puede exceder 500 caracteres")
    private String comment;

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productId;
}