package com.proyecto.mc_cotizacion.repository;

import com.proyecto.mc_cotizacion.entity.Quotation;
import com.proyecto.mc_cotizacion.entity.QuotationStatus;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation,Long> {
	@Transactional
	  @Modifying
	@Query("update Quotation q set q.status = :quotationStatus WHERE q.id = :id")
	int updateStatusById(Long id,QuotationStatus quotationStatus);
}
