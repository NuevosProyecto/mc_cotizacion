package com.proyecto.mc_cotizacion.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

import javax.persistence.*;

@Data
@Entity
@Table(name = "items_quotation")
@ApiModel("Model QuotationItem")
public class QuotationItem {
	
	@Column(name = "id")
    private Integer id;
	
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
    private BigDecimal unitAmount;

    @ApiModelProperty(value = "quantity")
    @Column(name = "quantity")
    private Integer quantity;

    @Transient
    private BigDecimal totalDetailAmount;

}
