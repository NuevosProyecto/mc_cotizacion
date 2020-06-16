package com.proyecto.mc_cotizacion.service;

import com.proyecto.mc_cotizacion.entity.QuotationItem;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface QuotationItemService {
	
	Completable save(QuotationItem model);
	Completable update(QuotationItem model);
	Single<QuotationItem> getById(Long id);
	Observable<QuotationItem> findAll();

}
