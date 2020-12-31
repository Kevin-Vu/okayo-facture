package com.okayo.facture.repository;

import com.okayo.facture.entity.referentiel.UserEntity;
import com.okayo.facture.entity.data.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

    List<InvoiceEntity> findAllByClient(UserEntity userEntity);

}
