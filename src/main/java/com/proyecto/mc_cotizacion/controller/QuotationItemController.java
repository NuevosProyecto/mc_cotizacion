package com.proyecto.mc_cotizacion.controller;

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
@RequestMapping(value="/items")
public class QuotationItemController {
	
	@Autowired
	private QuotationItemService quotationItemService;
	
	@PostMapping
	public Completable save(@RequestBody QuotationItemRequest model) {
		return quotationItemService.save(model);
	}
	
	@PutMapping
	public Completable update(@RequestBody QuotationItemRequest model) {
		return quotationItemService.update(model);
	}
	
	@GetMapping
	public Observable<QuotationItemResponse> findAll(){
		return quotationItemService.findAll();
	}
	
	@GetMapping("/{id}")
	public Single<QuotationItemResponse> getById(@PathVariable ("id") Long id)
	{
		return quotationItemService.getById(id);		
	}
	
}
