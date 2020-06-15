package com.proyecto.mc_cotizacion.service;

import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.sql.ClientInfoStatus;

public interface QuotationService {

    Completable saveQuotation(QuotationRequest model);

    Completable UpdateQuotation(QuotationRequest model);

    Single<QuotationResponse> getById(Integer id);

    Observable<QuotationResponse> findAll();

//Singles = String
//observable = lista
//    completable= void
}
