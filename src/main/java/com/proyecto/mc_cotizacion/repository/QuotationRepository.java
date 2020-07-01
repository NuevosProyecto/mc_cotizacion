package com.proyecto.mc_cotizacion.repository;

import com.proyecto.mc_cotizacion.entity.Quotation;
import com.proyecto.mc_cotizacion.entity.QuotationStatus;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation,Long> {
	
	@Query("from Quotation c where c.id=:id and c.status=:status")
	List<Quotation> getData(@Param("id") Long id,@Param("status") QuotationStatus status);
	
	@Query("from Quotation c where c.status=:status")
	List<Quotation> findByStatus(@Param("status") QuotationStatus status);
	
	@Transactional
	@Modifying
	@Query("update Quotation q set q.status = :quotationStatus WHERE q.id = :id")
	int updateStatusById(Long id,QuotationStatus quotationStatus);
	
}
