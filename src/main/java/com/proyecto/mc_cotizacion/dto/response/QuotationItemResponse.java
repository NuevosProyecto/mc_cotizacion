package com.proyecto.mc_cotizacion.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuotationItemResponse {
    private Long id;
    private Long idDetail;
    private String description;
    private Float unitAmount;
    private Integer quantity;
    private Float totalDetailAmount;
}
