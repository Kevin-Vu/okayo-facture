package com.circe.invoice.repository.referentiel;

import com.circe.invoice.entity.referentiel.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long> {
}
