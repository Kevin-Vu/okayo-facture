package com.circe.invoice.dto.designation;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DesignationDto {

    private Long id;
    private String name;
    private Float taxes;
    private Float unitPriceNoTaxes;
    private Integer quantity;
    private Float discount;
    private Float totalNoTaxes;

}