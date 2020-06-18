package com.proyecto.mc_cotizacion.service;


import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface QuotationService {

	Completable save(QuotationRequest model);
	Completable update(QuotationRequest model);
	Single<QuotationResponse> getById(Long id);
	Observable<QuotationResponse> findStatus(String status);
}
