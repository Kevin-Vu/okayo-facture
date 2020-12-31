package com.circe.invoice.repository;

import com.circe.invoice.entity.referentiel.DesignationCatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationCatalogRepository extends JpaRepository<DesignationCatalogEntity, Long> {

}
