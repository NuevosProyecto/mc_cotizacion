package com.proyecto.mc_cotizacion.controller;

import com.proyecto.mc_cotizacion.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.request.QuotationStatusRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;
import com.proyecto.mc_cotizacion.dto.response.QuotationSummaryResponse;
import com.proyecto.mc_cotizacion.entity.QuotationStatus;
import com.proyecto.mc_cotizacion.service.QuotationService;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping (Constants.MAIN_PATH)
@Api(tags = "Quotation", description = "Esta API se encarga de la gestion de las cotizaciones")
@Slf4j
public class QuotationController {

	@Autowired
	QuotationService quotationService;
	
	@PostMapping
	@ApiOperation(value = "Metodo a traves del cual se envia la informacion de la  Cotizacion que  sera registrada dentro de la base de datos",
			notes = "Para el registro de la cotizacion , sera necesario el llenado de todo los campos , a excepcion de los Id´s los cuales seran generados automaticamente " )
	public ResponseEntity<Completable> save(@Valid @RequestBody QuotationRequest model) {
		log.info("Envio de parametros");
		try {
			return new ResponseEntity<>(quotationService.save(model),HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Metodo a traves del cual te permite cambiar datos especificos  de la Cotizacion, permitiendo asi contar con una base de datos actualizada",
			notes = "Es necesario el envio del Id de la cotizacion , junto a sus campos debidamente actualizados con la informacion mas reciente" )
	public ResponseEntity<Completable> update(@RequestBody QuotationRequest model) {
		log.info("Actualizacion de parametros");
		try {
			return new ResponseEntity<>(quotationService.update(model),HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping
	@ApiOperation(value = "Metodo a traves del cual te permite obtener la informacion de  todas las  Cotizacion que se encuentran almacenadas en la base de datos",
			notes = "Permite obtener todas as cotizaciones" )
	public ResponseEntity<Observable<QuotationResponse>> findAll(){
		log.info("Obtencion de datos");
		try {
			Observable<QuotationResponse> quotationResponse=quotationService.findAll();
			return new ResponseEntity<>(quotationResponse,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(Constants.ID)
	@ApiOperation(value = "Metodo a traves del cual te permite obtener los datos de una Cotizacion en especifico , a traves de la busqueda de su identificador (id)  ",
			notes = "Es necesario el envio del Id de la cotizacion ,para obtener  sus datos" )
	public ResponseEntity<Single<QuotationSummaryResponse>> getById(@PathVariable("id") Long id) {
		log.info("Obtencion de datos por id");
		try {
			Single<QuotationSummaryResponse> objQuotation=quotationService.getById(id);	
			return new ResponseEntity<>(objQuotation,HttpStatus.ACCEPTED);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(Constants.STATUS)
	@ApiOperation(value = "Metodo a traves del cual te permite obtener los datos de una o varias  Cotizaciones , a traves de su Status",
			notes = "Es necesario el envio del Status de la cotizacion ,para obtener  los datos de una o varias  Cotizaciones , a traves de su Status" )
	public ResponseEntity<Observable<QuotationResponse>> findStatus(@Valid @PathVariable ("status") QuotationStatus status) {
		log.info("Obtencion de datos por status");
		try {
			Observable<QuotationResponse> objQuotation=quotationService.findStatus(status);	
			return new ResponseEntity<>(objQuotation,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}	
	
	@PutMapping(Constants.PUTSTATUS)
	@ApiOperation(value = "Actualizar status de la cotizacion", notes = "Metodo Put para actualizar  el estado de la Cotizacion" )
	public ResponseEntity<Completable> updateStatus(@PathVariable("id") Long id, @RequestBody QuotationStatusRequest quotationStatusRequest) {
		log.info("Actualizacion status de la Cotizacion");
		try {
			return new ResponseEntity<>(quotationService.updateStatus(id, quotationStatusRequest), HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(quotationService.updateStatus(id, quotationStatusRequest),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/params")
	public ResponseEntity<Observable<QuotationResponse>> findByQueryParam(@RequestParam Map<String,String> params){
		try {
			Observable<QuotationResponse> objQuotation=quotationService.findQueryParam(params);
			return new ResponseEntity<>(objQuotation,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
}
