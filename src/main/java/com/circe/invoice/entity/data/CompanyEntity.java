package com.circe.invoice.entity.data;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "d_company")
public class CompanyEntity implements Serializable, Auditable<String, Integer, LocalDateTime> {

    @Id
    @SequenceGenerator(name = "company_generator", sequenceName = "company_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_generator")
    @Column(name = "com_id")
    private Integer id;

    @Column(name = "com_name", unique = true, nullable = false)
    private String name;

    @Column(name = "com_address", nullable = false)
    private String address;

    @Column(name = "com_city", nullable = false)
    private String city;

    @Column(name = "com_postal_code", nullable = false)
    private Integer postalCode;

    @Column(name = "com_created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "com_created_date")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "com_last_modified_by")
    @LastModifiedBy
    private String lastModifiedBy;

    @Column(name = "com_last_modified_date")
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
