package com.proyecto.mc_cotizacion.service.implement;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.mc_cotizacion.dao.QuotationDao;
import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.request.QuotationStatusRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;
import com.proyecto.mc_cotizacion.dto.response.QuotationSummaryResponse;
import com.proyecto.mc_cotizacion.entity.QuotationStatus;
import com.proyecto.mc_cotizacion.service.QuotationService;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Service
public class QuotationServiceImplement implements QuotationService {
	
	@Autowired
	private QuotationDao quotationDao;
	  
	@Override
	public Completable save(QuotationRequest model) {		
		return quotationDao.save(model);
	}

	@Override
	public Completable update(QuotationRequest model) {		
		return quotationDao.update(model);
	}

	@Override
	public Single<QuotationSummaryResponse> getById(Long id) {
		return quotationDao.getById(id);
	}

	@Override
	public Observable<QuotationResponse> findStatus(QuotationStatus status) {
		return quotationDao.findStatus(status);
	}

	@Override
	public Observable<QuotationResponse> findAll() {
		return quotationDao.findAll();
	}

	@Override
	public Completable updateStatus(Long id, QuotationStatusRequest quotationStatusRequest) {		
		return 	quotationDao.updateStatus(id, quotationStatusRequest);
	}	
	
	@Override
	public Observable<QuotationResponse> findQueryParam(Map<String, String> params) {
		return quotationDao.findQueryParam(params);
	}
	
}
