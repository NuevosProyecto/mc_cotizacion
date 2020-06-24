package com.proyecto.mc_cotizacion.controller;

import com.proyecto.mc_cotizacion.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;
import com.proyecto.mc_cotizacion.entity.QuotationStatus;
import com.proyecto.mc_cotizacion.service.QuotationService;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

import javax.validation.Valid;

@RestController
@RequestMapping (Constants.MAIN_PATH)
@Api(tags = "Quotation", description = "Esta API se encarga de la gestion de las cotizaciones")
@Slf4j
public class QuotationController {

	@Autowired
	QuotationService quotationService;
	
	@PostMapping
	@ApiOperation(value = "Registrar Cotizacion", notes = "Metodo Post para registrar  Cotizacion" )
	public Completable save( @Valid @RequestBody QuotationRequest model) {
		log.info("Envio de parametros");
		return  quotationService.save(model);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Actualizar Cotizacion", notes = "Metodo Put para actualizar  Cotizacion" )
	public Completable update(@RequestBody QuotationRequest model) {
		log.info("Actualizacion de parametros");
		return quotationService.update(model);
	}
	
	@GetMapping
	@ApiOperation(value = "Traer Cotizaciones", notes = "Metodo Get para traer todas las  Cotizacion" )
	public Observable<QuotationResponse> findAll(){
		log.info("Obtencion de datos");
		return quotationService.findAll();
	}

	@GetMapping(Constants.ID)
	@ApiOperation(value = "Traer Cotizacion por Id", notes = "Metodo Get para traer una Cotizacion por Id" )
	public Single<QuotationResponse> getById(@PathVariable ("id") Long id) {
		log.info("Obtencion de datos por id");
		return quotationService.getById(id);		
	}

	@GetMapping(Constants.STATUS)
	@ApiOperation(value = "Traer Cotizaciones por estatus", notes = "Metodo Get para traer una lista de Cotizaciones por estatus" )
	public Observable<QuotationResponse> findStatus(@Valid @PathVariable ("status") QuotationStatus status) {
		log.info("Obtencion de datos por status");
		return quotationService.findStatus(status);
	}	
	
}
