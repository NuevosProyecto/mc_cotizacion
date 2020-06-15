package com.proyecto.mc_cotizacion.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "detailQuotation")
@ApiModel("Model DetailQuotation")
public class DetailQuotation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idDetail")
    @ApiModelProperty(value = "idDetail")
    @Column(name = "idDetail")
    private Long idDetail;

    @ApiModelProperty(value = "desciption")
    @Column(name = "desciption")
    private  String desciption;

    @ApiModelProperty(value = "unitAmount")
    @Column(name = "unitAmount")
    private Integer unitAmount;

    @ApiModelProperty(value = "quantity")
    @Column(name = "quantity")
    private Integer quantity;

    @ApiModelProperty(value = "totalDetailAmount")
    @Column(name = "totalDetailAmount")
    private Integer totalDetailAmount;

}
