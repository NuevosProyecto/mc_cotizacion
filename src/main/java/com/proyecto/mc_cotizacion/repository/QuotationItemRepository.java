package com.proyecto.mc_cotizacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.mc_cotizacion.entity.QuotationItem;


public interface QuotationItemRepository extends JpaRepository<QuotationItem,Long> {

}
