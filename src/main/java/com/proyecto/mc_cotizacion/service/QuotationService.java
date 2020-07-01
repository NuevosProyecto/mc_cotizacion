package com.proyecto.mc_cotizacion.service;


import java.util.Map;
import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;
import io.reactivex.Completable;
import io.reactivex.Observable;

public interface QuotationService {
	Completable save(QuotationRequest model);
	Completable update(QuotationRequest model);
	Observable<QuotationResponse> getData(Map<String,String> params);
}
