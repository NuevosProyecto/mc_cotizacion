package com.proyecto.mc_cotizacion.dto.request;

import javax.validation.constraints.NotBlank;

import com.proyecto.mc_cotizacion.entity.QuotationStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel("Model QuotationStatusRequest")
public class QuotationStatusRequest {
    @ApiModelProperty(value = "status")
    private QuotationStatus status;

}
