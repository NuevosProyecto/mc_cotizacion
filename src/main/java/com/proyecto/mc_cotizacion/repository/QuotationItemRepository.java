package com.proyecto.mc_cotizacion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.mc_cotizacion.entity.QuotationItem;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationItemRepository extends JpaRepository<QuotationItem,Long> {

}
