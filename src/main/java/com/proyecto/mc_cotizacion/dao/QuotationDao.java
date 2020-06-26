package com.proyecto.mc_cotizacion.dao;

import java.util.Map;

import javax.ws.rs.core.Response;

import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.request.QuotationStatusRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;
import com.proyecto.mc_cotizacion.dto.response.QuotationSummaryResponse;
import com.proyecto.mc_cotizacion.entity.QuotationStatus;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface QuotationDao {
	
	Completable save(QuotationRequest model);
	Completable update(QuotationRequest model);
	Single<QuotationSummaryResponse> getById(Long id);
	Observable<QuotationResponse> findStatus(QuotationStatus status);
	Observable<QuotationResponse> findAll();
	Observable<Response> updateStatus(Long id, QuotationStatusRequest quotationStatusRequest);
	Observable<QuotationResponse> findQueryParam(Map<String,String> params);
}
