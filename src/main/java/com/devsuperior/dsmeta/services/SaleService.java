package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleSellerMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SaleSellerMinDTO> listarVendasVendedor(String minDate, String maxDate) {
		LocalDate dataInicialConvertida = converterDataInicial(minDate,maxDate);
		LocalDate dataFinalConvertida = converterDataFinal(maxDate);

		return repository.relatorioVendasVendedor(dataInicialConvertida, dataFinalConvertida);
	}

	public Page<SaleMinDTO> listarVendasPaginadas(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate dataInicialConvertida = converterDataInicial(minDate,maxDate);
		LocalDate dataFinalConvertida = converterDataFinal(maxDate);

		return repository.relatorioVendas(dataInicialConvertida, dataFinalConvertida, name, pageable);
	}

	private LocalDate converterDataInicial(String dataInicial, String dataFinal) {
		if (dataInicial == null || dataInicial.isEmpty()) {
			if (dataFinal == null || dataFinal.isEmpty()) {
				return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1L);
			} else {
				return LocalDate.parse(dataFinal).minusYears(1L);
			}
		} else {
			return LocalDate.parse(dataInicial);
		}
	}

	private LocalDate converterDataFinal(String dataFinal) {
		if (dataFinal == null || dataFinal.isEmpty()) {
			return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			return LocalDate.parse(dataFinal);
		}
	}
}
