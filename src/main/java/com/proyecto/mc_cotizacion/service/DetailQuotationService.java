package com.proyecto.mc_cotizacion.service;

import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;

import io.reactivex.Completable;

public interface DetailQuotationService {
	 Completable saveDetailQuotation(QuotationRequest model);
	 Completable UpdateDetailQuotation(QuotationRequest model);
}
