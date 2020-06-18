package com.proyecto.mc_cotizacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;
import com.proyecto.mc_cotizacion.entity.QuotationStatus;
import com.proyecto.mc_cotizacion.service.QuotationService;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@RestController
@RequestMapping (value="/quotation")
public class QuotationController {
	@Autowired
	QuotationService quotationService;
	
	@PostMapping
	public Completable save( @RequestBody QuotationRequest model)
	{
		return  quotationService.save(model);
	}
	@PutMapping("/{id}")
	public Completable update(@RequestBody QuotationRequest model)
	{
		return quotationService.update(model);
	}
	
	@GetMapping
	public Observable<QuotationResponse> findAll(){
		return quotationService.findAll();
	}
	
	@GetMapping("/id/{id}")
	public Single<QuotationResponse> getById(@PathVariable ("id") Long id)
	{
		return quotationService.getById(id);		
	}
	
	@GetMapping("/status/{status}")
	public Observable<QuotationResponse> findStatus(@PathVariable ("status") QuotationStatus status)
	{
		return quotationService.findStatus(status);
	}	
	
}
