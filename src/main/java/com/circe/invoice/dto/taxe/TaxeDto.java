package com.circe.invoice.dto.taxe;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaxeDto {

    private Float amount;
    private Timestamp dateFacturation;
    private String productType;
}
