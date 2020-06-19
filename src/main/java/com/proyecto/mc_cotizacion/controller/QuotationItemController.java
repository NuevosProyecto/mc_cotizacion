package com.proyecto.mc_cotizacion.controller;

import com.proyecto.mc_cotizacion.util.Constants;
import io.swagger.annotations.ApiOperation;
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

import com.proyecto.mc_cotizacion.dto.request.QuotationItemRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationItemResponse;
import com.proyecto.mc_cotizacion.service.QuotationItemService;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@RestController
@RequestMapping(Constants.MAIN_PATH2)
public class QuotationItemController {

private static final Logger logger = LogManager.getLogger(QuotationItemController.class);
	
	@Autowired
	private QuotationItemService quotationItemService;
	
	@PostMapping
	@ApiOperation(value = "Registrar QuotationItem", notes = "Metodo Post para registrar  QuotationItem" )
	public Completable save(@RequestBody QuotationItemRequest model) {
		logger.info("Envio de parametros");
		return quotationItemService.save(model);
	}
	
	@PutMapping
	@ApiOperation(value = "Actualizar QuotationItem", notes = "Metodo Put para actualizar  QuotationItem" )
	public Completable update(@RequestBody QuotationItemRequest model) {
		logger.info("Actualizacion de parametros");
		return quotationItemService.update(model);
	}
	
	@GetMapping
	@ApiOperation(value = "Traer QuotationItem", notes = "Metodo Get para traer todas las  Cotizacion" )
	public Observable<QuotationItemResponse> findAll(){
		logger.info("Obtencion de datos");
		return quotationItemService.findAll();
	}

	@GetMapping(Constants.ID)
	@ApiOperation(value = "Traer QuotationItem por Id", notes = "Metodo Get para traer una QuotationItem por Id" )
	public Single<QuotationItemResponse> getById(@PathVariable ("id") Long id) {
		logger.info("Obtencion de datos por id");
		return quotationItemService.getById(id);		
	}
	
}
