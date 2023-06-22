package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleMinDTO {

	private Long id;
	private Double amount;
	private LocalDate date;
	private String vendedor;

	public SaleMinDTO(Long id, Double amount, LocalDate date,String vendedor) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.vendedor = vendedor;
	}

	public SaleMinDTO(String vendedor, Double amount) {
		this.vendedor = vendedor;
		this.amount = amount;
	}

	public SaleMinDTO(Sale entity) {
		id = entity.getId();
		amount = entity.getAmount();
		date = entity.getDate();
		vendedor = entity.getSeller().getName();
	}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getVendedor() {
		return vendedor;
	}
}
