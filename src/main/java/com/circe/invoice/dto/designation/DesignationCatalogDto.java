package com.circe.invoice.dto.designation;

import com.circe.invoice.dto.taxe.TaxeDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DesignationCatalogDto {

    private String name;
    private Float priceNoTaxes;
    private TaxeDto taxeDto;

}
