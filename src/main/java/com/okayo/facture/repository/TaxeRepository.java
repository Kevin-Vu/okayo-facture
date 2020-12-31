package com.okayo.facture.repository;

import com.okayo.facture.entity.referentiel.TaxeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxeRepository extends JpaRepository<TaxeEntity, Long> {
}
