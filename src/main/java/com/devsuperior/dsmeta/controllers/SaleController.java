package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleSellerMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public List<SaleSellerMinDTO> listarVendasVendedor(@RequestParam(required = false) String minDate,
                                                       @RequestParam(required = false) String maxDate) {
		return service.listarVendasVendedor(minDate, maxDate);
	}

	@GetMapping(value = "/report")
	public Page<SaleMinDTO> listarVendasPaginadas(@RequestParam(required = false) String minDate,
												  @RequestParam(required = false) String maxDate,
												  @RequestParam(required = false, defaultValue = "") String name,
												  @PageableDefault(page = 0, size = 10) Pageable pageable) {
		return service.listarVendasPaginadas(minDate, maxDate, name, pageable);
	}

}
