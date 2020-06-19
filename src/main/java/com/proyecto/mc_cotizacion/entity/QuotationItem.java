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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "items_quotation")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuotationItem {
	
	@Column(name = "id")
    private Long id;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_detail")
    @Column(name = "id_detail")
    private Long idDetail;

    @Column(name = "description")
    private  String description;

    @Column(name = "unit_amount")
    private Float unitAmount;

    @Column(name = "quantity")
    private Integer quantity;

    @Transient
    private Float totalDetailAmount;

}
