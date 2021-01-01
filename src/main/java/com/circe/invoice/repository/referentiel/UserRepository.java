package com.circe.invoice.repository.referentiel;

import com.circe.invoice.entity.referentiel.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserCode(String userCode);
}
