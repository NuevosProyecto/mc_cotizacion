package com.proyecto.mc_cotizacion.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "quotation")
@ApiModel("Model Quotation")
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
    @ApiModelProperty(value = "id")
    @Column(name = "Id")
    private Long id;

    @ApiModelProperty(value = "numberQuotation")
    @Column(name = "numberQuotation")
    private String numberQuotation;

    @ApiModelProperty(value = "client")
    @Column(name = "client")
    private String client;

    @ApiModelProperty(value = "dateQuotation")
    @Column(name = "dateQuotation")
    private Date dateQuotation;

    @ApiModelProperty(value = "totalAmount")
    @Column(name = "totalAmount")
    private String totalAmount;

    @ApiModelProperty(value = "status")
    @Column(name = "status")
    private String status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name= "idDetail")
    private Integer idDetail;
}
