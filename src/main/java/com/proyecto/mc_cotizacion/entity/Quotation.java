package com.proyecto.mc_cotizacion.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @Transient
    private Float totalAmount;

    @Column(name = "status")
    private QuotationStatus status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name= "id")    
    private List<QuotationItem> items;
}
