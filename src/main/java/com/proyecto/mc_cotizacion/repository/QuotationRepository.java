package com.proyecto.mc_cotizacion.repository;

import com.proyecto.mc_cotizacion.entity.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotationRepository extends JpaRepository<Quotation,Integer> {
}
