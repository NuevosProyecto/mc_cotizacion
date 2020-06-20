package com.proyecto.mc_cotizacion.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuotationItemResponse {
    private Long id;
    private Long idDetail;
    private String description;
    private BigDecimal unitAmount;
    private Integer quantity;
    private BigDecimal totalDetailAmount;
}
