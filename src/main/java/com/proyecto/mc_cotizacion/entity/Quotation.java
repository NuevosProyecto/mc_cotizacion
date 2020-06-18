package com.proyecto.mc_cotizacion.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "quotations")
@ApiModel("Model Quotations")
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
    @ApiModelProperty(value = "id")
    @Column(name = "Id")
    private Long id;

    @ApiModelProperty(value = "number_quotation")
    @Column(name = "number_quotation")
    private String numberQuotation;

    @ApiModelProperty(value = "client")
    @Column(name = "client")
    private String client;

    @ApiModelProperty(value = "date_quotation")
    @Column(name = "date_quotation")
    private Date dateQuotation;

    @Transient
    private Float totalAmount;

    @ApiModelProperty(value = "status")
    @Column(name = "status")
    private QuotationStatus status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name= "id")    
    private List<QuotationItem> items;
}
