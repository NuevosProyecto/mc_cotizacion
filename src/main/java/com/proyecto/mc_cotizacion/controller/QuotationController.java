package com.proyecto.mc_cotizacion.controller;

import com.proyecto.mc_cotizacion.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.request.QuotationStatusRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;
import com.proyecto.mc_cotizacion.dto.response.QuotationStatusResponse;
import com.proyecto.mc_cotizacion.service.QuotationService;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.ArrayList;
import java.util.List;
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
	@ApiOperation(value = Constants.SAVE_VALUE,notes =Constants.SAVE_NOTE )
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public Completable save(@Valid @RequestBody QuotationRequest model) {
		log.info("Envio de parametros");
		return quotationService.save(model);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = Constants.UPDATE_VALUE,notes = Constants.UPDATE_NOTE )
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public Completable update(@RequestBody QuotationRequest model) {		
		log.info("Actualizacion de parametros");		
		return quotationService.update(model);		
	}
	
	@GetMapping
	@ApiOperation(value = Constants.GET_VALUE,notes = Constants.GET_NOTE )
	public Observable<QuotationResponse> getData(@RequestParam Map<String,String> params){
			return quotationService.getData(params);
	}
	
	@PutMapping(Constants.STATUS)
	@ApiOperation(value = Constants.UPDATESTATUS_VALUE, notes = Constants.UPDATESTATUS_NOTE)
	public Observable<QuotationStatusResponse> updateStatus(@PathVariable("id") Long id, @RequestBody QuotationStatusRequest quotationStatusRequest) {
		log.info("Actualizacion status de la Cotizacion");			
			return quotationService.updateStatus(id, quotationStatusRequest);

	}
}
