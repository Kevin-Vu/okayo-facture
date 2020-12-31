package com.circe.invoice.repository;

import com.circe.invoice.entity.referentiel.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxeRepository extends JpaRepository<ProductTypeEntity, Long> {
}
