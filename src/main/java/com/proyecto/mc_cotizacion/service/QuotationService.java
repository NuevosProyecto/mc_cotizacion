package com.proyecto.mc_cotizacion.service;


import java.util.List;
import java.util.Map;
import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.request.QuotationStatusRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;
import com.proyecto.mc_cotizacion.dto.response.QuotationStatusResponse;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface QuotationService {
	Completable save(QuotationRequest model);
	Completable update(QuotationRequest model);
	Observable<QuotationResponse> getData(Map<String,String> params);
	Observable<QuotationStatusResponse> updateStatus(Long id, QuotationStatusRequest quotationStatusRequest);
}
