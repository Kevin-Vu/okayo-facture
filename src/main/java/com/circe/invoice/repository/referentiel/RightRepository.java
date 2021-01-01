package com.circe.invoice.repository.referentiel;

import com.circe.invoice.entity.referentiel.RightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RightRepository extends JpaRepository<RightEntity, Long> {
}
