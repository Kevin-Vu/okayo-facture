package com.circe.invoice.repository.data;

import com.circe.invoice.entity.data.CustomerEntity;
import com.circe.invoice.entity.data.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

    List<InvoiceEntity> findAllByCustomer(CustomerEntity customerEntity);

}
