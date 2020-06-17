package com.proyecto.mc_cotizacion.dao.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.proyecto.mc_cotizacion.dao.QuotationDao;
import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;
import com.proyecto.mc_cotizacion.repository.QuotationRepository;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class QuotationDaoImplement implements QuotationDao{

	private QuotationRepository quotationRepository;
	
	@Override
	public Completable save(QuotationRequest model) {
		

		return null;
	}

	@Override
	public Completable update(QuotationRequest model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Single<QuotationResponse> getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<QuotationResponse> findStatus() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
