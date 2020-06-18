package com.proyecto.mc_cotizacion.dao.implement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class QuotationDaoImplement implements QuotationDao {

    private QuotationRepository quotationRepository;

    @Override
    public Completable save(QuotationRequest model) {
        return Completable.fromAction(() -> quotationRepository.save(toQuotes(model)));
    }

    private Quotation toQuotes(QuotationRequest model) {
        Quotation quotation = new Quotation();
        quotation.setNumberQuotation(model.getNumberQuotation());
        quotation.setClient(model.getClient());
        quotation.setDateQuotation(model.getDateQuotation());
        //quotation.setTotalAmount(getTotalAmount(getListItem(model.getItems())));
        quotation.setStatus(model.getStatus());
        quotation.setItems(getListItem(model.getItems()));

        return quotation;
    }
    
    private List<QuotationItem> getListItem(List<QuotationItemRequest> items) {

        List<QuotationItem> listItem = new ArrayList<>();
        for (QuotationItemRequest item : items) {
        	/*
            QuotationItem quotationItem = QuotationItem.builder()
            		.id(item.getId())
                    .idDetail(item.getIdDetail())
                    .description(item.getDescription())
                    .unitAmount(item.getUnitAmount())
                    .quantity(item.getQuantity())
                    //.totalDetailAmount(getTotalAmountItems(item.getUnitAmount(),item.getQuantity()))
                    .build();
            */
        	 QuotationItem quotationItem = new QuotationItem();
        	 quotationItem.setId(item.getId());
        	 quotationItem.setIdDetail(item.getIdDetail());
        	 quotationItem.setDescription(item.getDescription());
        	 quotationItem.setUnitAmount(item.getUnitAmount());
        	 quotationItem.setQuantity(item.getQuantity());
             //quotationItem.setTotalDetailAmount(getTotalAmountItems(item.getUnitAmount(),item.getQuantity()));
            listItem.add(quotationItem);
        }

        return listItem;
    }
    
    @Override
    public Completable update(QuotationRequest model) {
        return maybeAt(model.getId()).flatMapCompletable(quotation -> {
            return save(model);
        });
    }

    private Maybe<Quotation> maybeAt(Long idQuote) {
        return Maybe.just(quotationRepository.findById(idQuote).orElse(new Quotation())).switchIfEmpty(Maybe.empty());
    }

    @Override
    public Single<QuotationResponse> getById(Long id) {
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
    public Observable<QuotationResponse> findStatus(String status) {
        return Observable.fromIterable(quotationRepository.findAll())
                .map(quotation -> {
                    QuotationResponse quotationResponse = new QuotationResponse();
                    quotationResponse.setId(quotation.getId());
                    quotationResponse.setNumberQuotation(quotation.getNumberQuotation());
                    quotationResponse.setClient(quotation.getClient());
                    quotationResponse.setDateQuotation(quotation.getDateQuotation());
                    quotationResponse.setTotalAmount(getTotalAmount(getListItemResponse(quotation.getItems())));
                    quotationResponse.setStatus(quotation.getStatus());
                    quotationResponse.setItems(getListItemResponse(quotation.getItems()));
                    return quotationResponse;
                })
                .filter(s ->  status.equals(s.getStatus()))
                .subscribeOn(Schedulers.io())
                ;
    }

	@Override
	public Observable<QuotationResponse> findAll() {
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
