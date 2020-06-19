package com.proyecto.mc_cotizacion.service.implement;

import com.proyecto.mc_cotizacion.controller.QuotationItemController;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.mc_cotizacion.dao.QuotationDao;
import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;
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
	public Single<QuotationResponse> getById(Long id) {
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

	
	
}
