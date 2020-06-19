package com.proyecto.mc_cotizacion.dao.implement;

import com.proyecto.mc_cotizacion.service.implement.QuotationServiceImplement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(QuotationItemDaoImplement.class);

    private QuotationItemRepository quotationItemRepository;

    @Override
    public Completable save(QuotationItemRequest model) {
        return Completable.fromAction(() -> quotationItemRepository.save(toQuotes(model)));
    }

    private QuotationItem toQuotes(QuotationItemRequest model) {
        logger.info("seteo de datos del metodo save");
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
		logger.info("actualizando y guardando los campos");
        return maybeAt(model.getId()).flatMapCompletable(quotationItem -> {
            return save(model);
        });
    }

    private Maybe<QuotationItem> maybeAt(Long idItem) {
		logger.info("buscando por id y obteniendo los campos");
        return Maybe.just(quotationItemRepository.findById(idItem)
                .orElse(new QuotationItem()))
                .switchIfEmpty(Maybe.empty());
    }

    @Override
    public Single<QuotationItemResponse> getById(Long id) {
        logger.info("seteo de datos por Id");
        return maybeAt(id).map(quotationItem -> {
            QuotationItemResponse quotationItemResponse = new QuotationItemResponse();
            quotationItemResponse.setId(quotationItem.getId());
            quotationItemResponse.setIdDetail(quotationItem.getIdDetail());
            quotationItemResponse.setDescription(quotationItem.getDescription());
            quotationItemResponse.setUnitAmount(quotationItem.getUnitAmount());
            quotationItemResponse.setQuantity(quotationItem.getQuantity());
            quotationItemResponse.setTotalDetailAmount(getTotalAmountItems(quotationItem.getUnitAmount(), quotationItem.getQuantity()));
            return quotationItemResponse;
        }).toSingle();
    }

    @Override
    public Observable<QuotationItemResponse> findAll() {
		logger.info("seteo de todos los datos registrados");
        return Observable.fromIterable(quotationItemRepository.findAll())
                .map(quotationItem -> {
                    QuotationItemResponse quotationItemResponse = new QuotationItemResponse();
                    quotationItemResponse.setId(quotationItem.getId());
                    quotationItemResponse.setIdDetail(quotationItem.getIdDetail());
                    quotationItemResponse.setDescription(quotationItem.getDescription());
                    quotationItemResponse.setUnitAmount(quotationItem.getUnitAmount());
                    quotationItemResponse.setQuantity(quotationItem.getQuantity());
                    quotationItemResponse.setTotalDetailAmount(getTotalAmountItems(quotationItem.getUnitAmount(), quotationItem.getQuantity()));
                    return quotationItemResponse;
                })
                .subscribeOn(Schedulers.io());
    }

    private Float getTotalAmountItems(Float unitAmount, Integer quantity) {
        Float total = 0f;
        total = unitAmount * quantity;
        return total;
    }

}
