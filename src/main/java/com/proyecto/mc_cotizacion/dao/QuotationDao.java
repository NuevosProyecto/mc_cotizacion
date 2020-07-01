package com.proyecto.mc_cotizacion.dao;
import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;
import com.proyecto.mc_cotizacion.entity.QuotationStatus;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface QuotationDao {
	
	Completable save(QuotationRequest model);
	Completable update(QuotationRequest model);
	Observable<QuotationResponse> getById(Long id);
	Observable<QuotationResponse> findByStatus(QuotationStatus status);
	Observable<QuotationResponse> getData(Long id, QuotationStatus status);
}
