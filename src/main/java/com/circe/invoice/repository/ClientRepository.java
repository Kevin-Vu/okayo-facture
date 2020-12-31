package com.circe.invoice.repository;

import com.circe.invoice.entity.referentiel.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByClientCode(String clientCode);
    boolean existsClientEntityByIdAndCompanyEntityName(Long id, String company);
}
