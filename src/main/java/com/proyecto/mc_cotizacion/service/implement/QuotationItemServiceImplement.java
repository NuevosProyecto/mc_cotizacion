package com.proyecto.mc_cotizacion.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.mc_cotizacion.dao.QuotationItemDao;
import com.proyecto.mc_cotizacion.dto.request.QuotationItemRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationItemResponse;
import com.proyecto.mc_cotizacion.entity.QuotationItem;
import com.proyecto.mc_cotizacion.service.QuotationItemService;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Service
public class QuotationItemServiceImplement implements QuotationItemService{

	@Autowired
	private QuotationItemDao quotationItemDao;
	
	@Override
	public Completable save(QuotationItemRequest model) {
		// TODO Auto-generated method stub
		return quotationItemDao.save(model);
	}

	@Override
	public Completable update(QuotationItemRequest model) {
		// TODO Auto-generated method stub
		return quotationItemDao.update(model);
	}

	@Override
	public Single<QuotationItemResponse> getById(Long id) {
		// TODO Auto-generated method stub
		return quotationItemDao.getById(id);
	}

	@Override
	public Observable<QuotationItemResponse> findAll() {
		// TODO Auto-generated method stub
		return quotationItemDao.findAll();
	}

}
