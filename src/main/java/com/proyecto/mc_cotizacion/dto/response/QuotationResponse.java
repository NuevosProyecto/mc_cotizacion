package com.proyecto.mc_cotizacion.dto.response;

import com.proyecto.mc_cotizacion.entity.QuotationStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuotationResponse {

    private Long id;
    private String numberQuotation;
    private String client;
    private Date dateQuotation;
    private BigDecimal totalAmount;
    private QuotationStatus status;

    @Singular
    private List<QuotationItemResponse> items;
}
