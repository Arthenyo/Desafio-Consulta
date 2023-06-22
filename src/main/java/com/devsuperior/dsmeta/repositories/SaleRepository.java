package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSellerMinDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj.id, obj.amount, obj.date, obj.seller.name) " +
           "FROM Sale obj " +
            "WHERE obj.date >= :minDate AND obj.date < :maxDate AND LOWER (obj.seller.name) LIKE CONCAT('%',LOWER(:name),'%')")
    Page<SaleMinDTO> relatorioVendas(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);
    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSellerMinDTO(obj.seller.name, SUM(obj.amount)) " +
            "FROM Sale obj " +
            "WHERE obj.date >= :minDate AND obj.date <= :maxDate " +
            "GROUP BY obj.seller.name")
    List<SaleSellerMinDTO> relatorioVendasVendedor(LocalDate minDate, LocalDate maxDate);

}
