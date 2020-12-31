package com.circe.invoice.entity.referentiel;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "r_product_type")
public class ProductTypeEntity implements Auditable<String, Integer, LocalDateTime> {

    @Id
    @SequenceGenerator(name = "product_type_generator", sequenceName = "product_type_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_type_generator")
    @Column(name = "pro_id")
    private Long id;

    @Column(name = "pro_product_type")
    private String productType;

    @Column(name = "pro_amount", scale = 2, nullable = false)
    private Float amount;

    @Column(name = "pro_expirity_date", nullable = false)
    private Timestamp expirityDate;

    @OneToMany(mappedBy = "productType", fetch = FetchType.LAZY)
    private List<DesignationCatalogEntity> designationCatalogList = new ArrayList<>();

    @Column(name = "pro_created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "pro_created_date")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "pro_last_modified_by")
    @LastModifiedBy
    private String lastModifiedBy;

    @Column(name = "pro_last_modified_date")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Override
    public Optional<String> getCreatedBy() {
        return Optional.ofNullable(this.createdBy);
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Optional<LocalDateTime> getCreatedDate() {
        return Optional.ofNullable(this.createDate);
    }

    @Override
    public void setCreatedDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public Optional<String> getLastModifiedBy() {
        return Optional.ofNullable(this.lastModifiedBy);
    }

    @Override
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public Optional<LocalDateTime> getLastModifiedDate() {
        return Optional.ofNullable(this.lastModifiedDate);
    }

    @Override
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }
}
