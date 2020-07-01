package com.proyecto.mc_cotizacion.service.implement;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.mc_cotizacion.dao.QuotationDao;
import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;
import com.proyecto.mc_cotizacion.entity.QuotationStatus;
import com.proyecto.mc_cotizacion.service.QuotationService;

import io.reactivex.Completable;
import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;
@Slf4j
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
	public Observable<QuotationResponse> getData(Map<String, String> params) {
		log.info("Search Dynamic");
		
		Observable<QuotationResponse> obsQuotationResponse=null;
		Long id;QuotationStatus status;
		if(!params.isEmpty()) {
			if(params.get("id")!=null && !"".equals(params.get("id")) && params.get("status")!=null && !"".equals(params.get("status"))) {
				id=new Long(params.get("id"));
				status=QuotationStatus.valueOf(params.get("status"));
				log.info("Po id y estado");
				obsQuotationResponse=quotationDao.getData(id, status);
				
			}else if((params.get("id")==null || "".equals(params.get("id"))) && params.get("status")!=null && !"".equals(params.get("status"))) {
				status=QuotationStatus.valueOf(params.get("status"));
				obsQuotationResponse=quotationDao.findByStatus(status);
				log.info("por estado");
			}else if (params.get("id")!=null && !"".equals(params.get("id"))){
				id=new Long(params.get("id"));
				obsQuotationResponse=quotationDao.getById(id);
				log.info("por id");
			}
		}
		
		return obsQuotationResponse;
	}
	
}
