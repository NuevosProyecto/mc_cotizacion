package com.proyecto.mc_cotizacion.dao.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import com.proyecto.mc_cotizacion.dao.QuotationItemDao;
import com.proyecto.mc_cotizacion.dto.request.QuotationItemRequest;
import com.proyecto.mc_cotizacion.dto.response.QuotationItemResponse;
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
@Slf4j
public class QuotationItemDaoImplement implements QuotationItemDao {

    private QuotationItemRepository quotationItemRepository;

    @Override
    public Completable save(QuotationItemRequest model) {
        return Completable.fromAction(() -> quotationItemRepository.save(toQuotes(model)));
    }

    private QuotationItem toQuotes(QuotationItemRequest model) {
        log.info("seteo de datos del metodo save");
        QuotationItem quotationItem = new QuotationItem();
        quotationItem.setId(model.getId());
        quotationItem.setIdDetail(model.getIdDetail());
        quotationItem.setDescription(model.getDescription());
        quotationItem.setUnitAmount(model.getUnitAmount());
        quotationItem.setQuantity(model.getQuantity());
        return quotationItem;
    }

    @Override
    public Completable update(QuotationItemRequest model) {
		log.info("actualizando y guardando los campos");
        return maybeAt(model.getId()).flatMapCompletable(quotationItem -> {
            return save(model);
        });
    }

    private Maybe<QuotationItem> maybeAt(Long idItem) {
		log.info("buscando por id y obteniendo los campos");
        return Maybe.just(quotationItemRepository.findById(idItem)
                .orElse(new QuotationItem()))
                .switchIfEmpty(Maybe.empty());
    }

    @Override
    public Single<QuotationItemResponse> getById(Long id) {
        log.info("seteo de datos por Id");
        return maybeAt(id).map(quotationItem -> {
            QuotationItemResponse quotationItemResponse = new QuotationItemResponse();
            quotationItemResponse.setId(quotationItem.getId());
            quotationItemResponse.setIdDetail(quotationItem.getIdDetail());
            quotationItemResponse.setDescription(quotationItem.getDescription());
            quotationItemResponse.setUnitAmount(quotationItem.getUnitAmount());
            quotationItemResponse.setQuantity(quotationItem.getQuantity());
            quotationItemResponse.setTotalDetailAmount(quotationItem.getTotalAmount());
            return quotationItemResponse;
        }).toSingle();
    }

    @Override
    public Observable<QuotationItemResponse> findAll() {
		log.info("seteo de todos los datos registrados");
        return Observable.fromIterable(quotationItemRepository.findAll())
                .map(quotationItem -> {
                    QuotationItemResponse quotationItemResponse = new QuotationItemResponse();
                    quotationItemResponse.setId(quotationItem.getId());
                    quotationItemResponse.setIdDetail(quotationItem.getIdDetail());
                    quotationItemResponse.setDescription(quotationItem.getDescription());
                    quotationItemResponse.setUnitAmount(quotationItem.getUnitAmount());
                    quotationItemResponse.setQuantity(quotationItem.getQuantity());
                    quotationItemResponse.setTotalDetailAmount(quotationItem.getTotalAmount());
                    return quotationItemResponse;
                })
                .subscribeOn(Schedulers.io());
    }
}
