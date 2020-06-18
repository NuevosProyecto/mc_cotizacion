package com.proyecto.mc_cotizacion.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Entity
@Table(name = "items_quotation")
@ApiModel("Model QuotationItem")
public class QuotationItem {
	
	@Column(name = "id")
    private Long id;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_detail")
    @ApiModelProperty(value = "id_detail")
    @Column(name = "id_detail")
    private Long idDetail;

    @ApiModelProperty(value = "description")
    @Column(name = "description")
    private  String description;

    @ApiModelProperty(value = "unit_amount")
    @Column(name = "unit_amount")
    private Float unitAmount;

    @ApiModelProperty(value = "quantity")
    @Column(name = "quantity")
    private Integer quantity;

    @Transient
    private Float totalDetailAmount;

}
