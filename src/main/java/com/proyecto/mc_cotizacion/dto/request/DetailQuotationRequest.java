package com.proyecto.mc_cotizacion.dto.request;

import javax.persistence.Column;

import io.swagger.annotations.ApiModelProperty;

public class DetailQuotationRequest {
	  	private Long idDetail;
	    private  String desciption;
	    private Integer unitAmount;
	    private Integer quantity;
	    private Integer totalDetailAmount;

}
