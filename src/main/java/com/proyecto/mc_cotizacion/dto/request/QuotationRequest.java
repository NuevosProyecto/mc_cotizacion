package com.proyecto.mc_cotizacion.dto.request;

import com.proyecto.mc_cotizacion.entity.QuotationItem;
import com.proyecto.mc_cotizacion.entity.QuotationStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@ApiModel("Model Quotations")
public class QuotationRequest {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "number_quotation")
    private String numberQuotation;

    @ApiModelProperty(value = "client")
    private String client;

    @ApiModelProperty(value = "date_quotation")
    private Date dateQuotation;

    @ApiModelProperty(value = "status")
    private QuotationStatus status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<QuotationItemRequest> items;
}
