package com.proyecto.mc_cotizacion.controller;

import com.proyecto.mc_cotizacion.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;
import com.proyecto.mc_cotizacion.service.QuotationService;
import io.reactivex.Completable;
import io.reactivex.Observable;
import java.util.Map;
import javax.validation.Valid;

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
	public Completable update(@RequestBody QuotationRequest model) {
		log.info("Actualizacion de parametros");
		
		return quotationService.update(model);
		
	}
	
	//@ResponseStatus(value=HttpStatus.ACCEPTED, reason="No existen registros")
	@GetMapping
	public Observable<QuotationResponse> findByQueryParam(@RequestParam Map<String,String> params){
			return quotationService.getData(params);
	}
}
