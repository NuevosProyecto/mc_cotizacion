package com.proyecto.mc_cotizacion.dao.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.proyecto.mc_cotizacion.dao.QuotationDao;
import com.proyecto.mc_cotizacion.dto.request.QuotationItemRequest;
import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationItemResponse;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;
import com.proyecto.mc_cotizacion.entity.Quotation;
import com.proyecto.mc_cotizacion.entity.QuotationItem;
import com.proyecto.mc_cotizacion.entity.QuotationStatus;
import com.proyecto.mc_cotizacion.repository.QuotationRepository;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Repository
@Slf4j
public class QuotationDaoImplement implements QuotationDao {

    private QuotationRepository quotationRepository;

    @Override
    public Completable save(QuotationRequest model) {
        return Single.fromCallable(() -> toQuotes(model))
        		.map(quotationRepository::save)
        		.toCompletable();
    }

    private Quotation toQuotes(QuotationRequest model) {
		log.info("seteo de datos de Quotation del metodo save");
        Quotation quotation = new Quotation();
        quotation.setNumberQuotation(model.getNumberQuotation());
        quotation.setClient(model.getClient());
        quotation.setDateQuotation(model.getDateQuotation());
        quotation.setStatus(model.getStatus());
        quotation.setItems(getListItem(model.getItems()));

        return quotation;
    }
    
    private List<QuotationItem> getListItem(List<QuotationItemRequest> items) {
		log.info("seteo de datos de QuotationItems ");
		return items.stream()
		    .map(item -> {
        	 QuotationItem quotationItem = new QuotationItem();
        	 quotationItem.setId(item.getId());
        	 quotationItem.setIdDetail(item.getIdDetail());
        	 quotationItem.setDescription(item.getDescription());
        	 quotationItem.setUnitAmount(item.getUnitAmount());
        	 quotationItem.setQuantity(item.getQuantity());
            return quotationItem;
        }).collect(Collectors.toList());
    }
    
    @Override
    public Completable update(QuotationRequest model) {
		log.info("actualizando y guardando los campos");
        return maybeAt(model.getId()).flatMapCompletable(quotation -> {
            return save(model);
        });
    }

    private Maybe<Quotation> maybeAt(Long idQuote) {
		log.info("buscando por id y obteniendo los campos");
        return Maybe.just(
        		quotationRepository.findById(idQuote)
        		.orElseThrow(IllegalArgumentException::new))
        	.switchIfEmpty(Maybe.empty());
    }

    @Override
    public Single<QuotationResponse> getById(Long id) {
		log.info("seteo de datos por Id");
        return maybeAt(id).map(quotation -> {
            QuotationResponse quotationResponse = new QuotationResponse();
            quotationResponse.setId(quotation.getId());
            quotationResponse.setNumberQuotation(quotation.getNumberQuotation());
            quotationResponse.setClient(quotation.getClient());
            quotationResponse.setDateQuotation(quotation.getDateQuotation());
            quotationResponse.setTotalAmount(getTotalAmount(getListItemResponse(quotation.getItems())));
            quotationResponse.setStatus(quotation.getStatus());
            quotationResponse.setItems(getListItemResponse(quotation.getItems()));

            return quotationResponse;
        }).toSingle();
    }

	@Override
	public Observable<QuotationResponse> findAll() {
		log.info("seteo de todos los datos registrados");
		return Observable.fromIterable(quotationRepository.findAll())
				.map(quotation->{
					QuotationResponse quotationResponse=new QuotationResponse();
					quotationResponse.setId(quotation.getId());
					quotationResponse.setNumberQuotation(quotation.getNumberQuotation());
					quotationResponse.setClient(quotation.getClient());
					quotationResponse.setDateQuotation(quotation.getDateQuotation());
					quotationResponse.setTotalAmount(getTotalAmount(getListItemResponse(quotation.getItems())));
					quotationResponse.setStatus(quotation.getStatus());
					quotationResponse.setItems(getListItemResponse(quotation.getItems()));
					return quotationResponse;
				})
				.subscribeOn(Schedulers.io());
	}

	private List<QuotationItemResponse> getListItemResponse(List<QuotationItem> items) {
        List<QuotationItemResponse> listItem = new ArrayList<>();
        for (QuotationItem item : items) {
        	 QuotationItemResponse quotationItem = new QuotationItemResponse();
        	 quotationItem.setId(item.getId());
        	 quotationItem.setIdDetail(item.getIdDetail());
        	 quotationItem.setDescription(item.getDescription());
        	 quotationItem.setUnitAmount(item.getUnitAmount());
        	 quotationItem.setQuantity(item.getQuantity());
             quotationItem.setTotalDetailAmount(getTotalAmountItems(item.getUnitAmount(),item.getQuantity()));
            listItem.add(quotationItem);
        }

        return listItem;
    }
	
	@Override
    public Observable<QuotationResponse> findStatus(QuotationStatus status) {
		log.info("seteo de datos por Status");
		return     Observable.fromIterable(quotationRepository.findAll())
					.filter(x -> x.getStatus().equals(status))
					.map(quotation -> {
						QuotationResponse.QuotationResponseBuilder builder = QuotationResponse.builder()
								.id(quotation.getId())
								.client(quotation.getClient())
								.dateQuotation(quotation.getDateQuotation())
								.numberQuotation(quotation.getNumberQuotation())
								.items(getListItem2(quotation.getItems()))
								.totalAmount(getTotalAmount(getListItem2(quotation.getItems())))
								.status(quotation.getStatus());
						
						QuotationResponse cardB = builder.build();
                        return cardB;
						
					}).subscribeOn(Schedulers.io());
	}
    
    private List<QuotationItemResponse> getListItem2(List<QuotationItem> items){
		log.info("seteo de  los datos de QuotationItem iguales al Id de quotations");
		List<QuotationItemResponse> listItem=new ArrayList<>();
		for(QuotationItem item: items) {
			QuotationItemResponse quotationItem=QuotationItemResponse.builder().id(item.getId())
					.idDetail(item.getIdDetail())
					.description(item.getDescription())
					.unitAmount(item.getUnitAmount())
					.totalDetailAmount(getTotalAmountItems(item.getUnitAmount(),item.getQuantity()))
					.quantity(item.getQuantity())
					.build();
			listItem.add(quotationItem);
		}
		
		return listItem;
	}
	
    private Float getTotalAmountItems(Float unitAmount,Integer quantity) {
    	Float total=0f;
    	total=unitAmount*quantity;
    	return total;
    }
    
	private Float getTotalAmount(List<QuotationItemResponse> items) {
    	Float total=0f;
    	for (QuotationItemResponse item : items) {         
    		total+=item.getTotalDetailAmount();                     
        }
    	return total;
    }
    

}
