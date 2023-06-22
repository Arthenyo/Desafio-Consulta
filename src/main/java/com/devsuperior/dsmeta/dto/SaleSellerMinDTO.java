package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SaleSellerMinDTO {

	private Double amount;
	private String vendedor;


	public SaleSellerMinDTO(String vendedor, Double amount) {
		this.vendedor = vendedor;
		this.amount = amount;
	}

	public SaleSellerMinDTO(Sale entity) {
		amount = entity.getAmount();
		vendedor = entity.getSeller().getName();
	}

	public Double getAmount() {
		return amount;
	}

	public String getVendedor() {
		return vendedor;
	}
}
