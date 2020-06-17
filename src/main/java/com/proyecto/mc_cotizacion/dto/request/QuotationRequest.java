package com.proyecto.mc_cotizacion.dto.request;

import com.proyecto.mc_cotizacion.entity.QuotationItem;
import com.proyecto.mc_cotizacion.entity.QuotationStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class QuotationRequest {
    private Long id;
    private String numberQuotation;
    private String client;
    private Date dateQuotation;
    private String totalAmount;
    private QuotationStatus status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<QuotationItemRequest> items;
}
