package com.proyecto.mc_cotizacion.dao.implement;

import org.springframework.stereotype.Controller;

import com.proyecto.mc_cotizacion.dao.QuotationItemDao;
import com.proyecto.mc_cotizacion.dto.request.QuotationItemRequest;
import com.proyecto.mc_cotizacion.dto.request.QuotationRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationItemResponse;
import com.proyecto.mc_cotizacion.dto.response.QuotationResponse;
import com.proyecto.mc_cotizacion.entity.Quotation;
import com.proyecto.mc_cotizacion.entity.QuotationItem;
import com.proyecto.mc_cotizacion.repository.QuotationItemRepository;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class QuotationItemDaoImplement implements QuotationItemDao {

	private QuotationItemRepository quotationItemRepository;
	
	@Override
	public Completable save(QuotationItemRequest model) {
		return Completable.fromAction(() -> quotationItemRepository.save(toQuotes(model)));		
	}
	
	private QuotationItem toQuotes(QuotationItemRequest model) {
        QuotationItem quotationItem = new QuotationItem();
        quotationItem.setId(model.getId());
        quotationItem.setIdDetail(model.getIdDetail());
        quotationItem.setDescription(model.getDescription());
        quotationItem.setUnitAmount(model.getUnitAmount());
        quotationItem.setQuantity(model.getQuantity());
        //quotationItem.setItems(getListItem(model.getItems()));
        return quotationItem;
    }

	@Override
	public Completable update(QuotationItemRequest model) {
		return maybeAt(model.getId()).flatMapCompletable(quotationItem -> {
            return save(model);
        });
	}
	
	private Maybe<QuotationItem> maybeAt(Long idItem) {
		return Maybe.just(quotationItemRepository.findById(idItem).orElse(new QuotationItem())).switchIfEmpty(Maybe.empty());
    }

	@Override
	public Single<QuotationItemResponse> getById(Long id) {
		return maybeAt(id).map(quotationItem -> {
			QuotationItemResponse quotationItemResponse = new QuotationItemResponse();
			quotationItemResponse.setId(quotationItem.getId());
			quotationItemResponse.setIdDetail(quotationItem.getIdDetail());
			quotationItemResponse.setDescription(quotationItem.getDescription());
			quotationItemResponse.setUnitAmount(quotationItem.getUnitAmount());
			quotationItemResponse.setQuantity(quotationItem.getQuantity());
			quotationItemResponse.setTotalDetailAmount(getTotalAmountItems(quotationItem.getUnitAmount(),quotationItem.getQuantity()));
            return quotationItemResponse;
        }).toSingle();
	}

	@Override
	public Observable<QuotationItemResponse> findAll() {
		return Observable.fromIterable(quotationItemRepository.findAll())
				.map(quotationItem->{
					QuotationItemResponse quotationItemResponse=new QuotationItemResponse();
					quotationItemResponse.setId(quotationItem.getId());
					quotationItemResponse.setIdDetail(quotationItem.getIdDetail());
					quotationItemResponse.setDescription(quotationItem.getDescription());
					quotationItemResponse.setUnitAmount(quotationItem.getUnitAmount());
					quotationItemResponse.setQuantity(quotationItem.getQuantity());
					quotationItemResponse.setTotalDetailAmount(getTotalAmountItems(quotationItem.getUnitAmount(),quotationItem.getQuantity()));
					return quotationItemResponse;
				})
				.subscribeOn(Schedulers.io());
	}

    private Float getTotalAmountItems(Float unitAmount,Integer quantity) {
    	Float total=0f;
    	total=unitAmount*quantity;
    	return total;
    }
	
}
