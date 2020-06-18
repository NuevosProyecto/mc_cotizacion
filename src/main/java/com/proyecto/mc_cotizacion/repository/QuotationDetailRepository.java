package com.proyecto.mc_cotizacion.repository;

import java.util.List;

import com.proyecto.mc_cotizacion.entity.Quotation;

public interface QuotationDetailRepository {
    List<Quotation> findAllBydetailQuotationId(Long id);
}
