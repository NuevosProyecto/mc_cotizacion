package com.proyecto.mc_cotizacion.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Getter
@Setter
public class QuotationItemRequest {

    private Long id;
    private Long idDetail;
    private  String description;
    private Float unitAmount;
    private Integer quantity;
    //private Float totalDetailAmount;

}
