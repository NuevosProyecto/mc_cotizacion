package com.proyecto.mc_cotizacion.dto.request;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel("Model QuotationItem")
public class QuotationItemRequest {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "id_detail")
    private Long idDetail;

    @ApiModelProperty(value = "description")
    @NotBlank(message = "insert desription")
    private  String description;

    @ApiModelProperty(value = "unit_amount")
    @NotBlank(message = "insert unit Amount")
    private BigDecimal unitAmount;

    @ApiModelProperty(value = "quantity")
    @NotBlank(message = "insert date")
    private Integer quantity;

}
