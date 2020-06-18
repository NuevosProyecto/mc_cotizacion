package com.proyecto.mc_cotizacion.service;

import com.proyecto.mc_cotizacion.dto.request.QuotationItemRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationItemResponse;
import com.proyecto.mc_cotizacion.entity.QuotationItem;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface QuotationItemService {
	
	Completable save(QuotationItemRequest model);
	Completable update(QuotationItemRequest model);
	Single<QuotationItemResponse> getById(Long id);
	Observable<QuotationItemResponse> findAll();

}
