package com.proyecto.mc_cotizacion.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "quotations")
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
    @Column(name = "Id")
    private Long id;

    @Column(name = "number_quotation")
    private String numberQuotation;

    @Column(name = "client")
    @NotBlank(message = "no ingresado")
    private String client;

    @Column(name = "date_quotation")
    private Date dateQuotation;

    @Column(name = "status")
    private QuotationStatus status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name= "id")    
    private List<QuotationItem> items;
    
    public BigDecimal getTotalAmount() {
    	BigDecimal total = new BigDecimal(0.0).setScale(2);
    	for (QuotationItem item : items) {         
    		total=total.add(item.getTotalAmount().setScale(2));
        }
    	return total;
    }
}
