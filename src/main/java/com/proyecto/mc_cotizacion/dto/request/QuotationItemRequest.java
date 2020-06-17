package com.proyecto.mc_cotizacion.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
public class QuotationItemRequest {

    private Long id;
    private Long idDetail;
    private  String description;
    private BigDecimal unitAmount;
    private Integer quantity;
    private BigDecimal totalDetailAmount;

}
