package com.proyecto.mc_cotizacion.dao;

import com.proyecto.mc_cotizacion.entity.Quotation;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface QuotationDao {
	
	Completable save(Quotation model);
	Completable update(Quotation model);
	Single<Quotation> getById(Long id);
	Observable<Quotation> findStatus();
}
