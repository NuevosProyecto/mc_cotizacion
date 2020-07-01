package com.proyecto.mc_cotizacion.repository;

import com.proyecto.mc_cotizacion.entity.Quotation;
import com.proyecto.mc_cotizacion.entity.QuotationStatus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation,Long> {
	
	@Query("from Quotation c where c.id=:id and c.status=:status")
	List<Quotation> getData(@Param("id") Long id,@Param("status") QuotationStatus status);
	
	@Query("from Quotation c where c.status=:status")
	List<Quotation> findByStatus(@Param("status") QuotationStatus status);
	
}
