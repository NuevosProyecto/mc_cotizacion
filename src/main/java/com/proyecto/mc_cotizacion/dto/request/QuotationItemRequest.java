package com.proyecto.mc_cotizacion.dto.request;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel("Model QuotationItem")
public class QuotationItemRequest {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "id_detail")
    private Long idDetail;

    @ApiModelProperty(value = "description")
    private  String description;

    @ApiModelProperty(value = "unit_amount")
    private BigDecimal unitAmount;

    @ApiModelProperty(value = "quantity")
    private Integer quantity;

}
