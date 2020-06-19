package com.proyecto.mc_cotizacion.controller;

import com.proyecto.mc_cotizacion.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

@RestController
@RequestMapping (Constants.MAIN_PATH1)
@Api(value = "Microservicio Quotation", description = "Esta API se encarga de la gestion de las cotizaciones")
public class QuotationController {

	private static final Logger logger = LogManager.getLogger(QuotationController.class);

	@Autowired
	QuotationService quotationService;
	
	@PostMapping
	@ApiOperation(value = "Registrar Cotizacion", notes = "Metodo Post para registrar  Cotizacion" )
	public Completable save( @RequestBody QuotationRequest model) {
		logger.info("Envio de parametros");
		return  quotationService.save(model);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Actualizar Cotizacion", notes = "Metodo Put para actualizar  Cotizacion" )
	public Completable update(@RequestBody QuotationRequest model) {
//		logger.info("Actualizacion de parametros");
		return quotationService.update(model);
	}
	
	@GetMapping
	@ApiOperation(value = "Traer Cotizaciones", notes = "Metodo Get para traer todas las  Cotizacion" )
	public Observable<QuotationResponse> findAll(){
//		logger.info("Obtencion de datos");
		return quotationService.findAll();
	}

	@GetMapping(Constants.ID)
	@ApiOperation(value = "Traer Cotizacion por Id", notes = "Metodo Get para traer una Cotizacion por Id" )
	public Single<QuotationResponse> getById(@PathVariable ("id") Long id) {
//		logger.info("Obtencion de datos por id");
		return quotationService.getById(id);		
	}

	@GetMapping(Constants.STATUS)
	@ApiOperation(value = "Traer Cotizaciones por estatus", notes = "Metodo Get para traer una lista de Cotizaciones por estatus" )
	public Observable<QuotationResponse> findStatus(@PathVariable ("status") QuotationStatus status) {
//		logger.info("Obtencion de datos por status");
		return quotationService.findStatus(status);
	}	
	
}
