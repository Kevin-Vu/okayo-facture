package com.circe.invoice.dto.designation;

import com.circe.invoice.dto.taxe.TaxeDto;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DesignationCatalogDto {

    private String name;
    private Float priceNoTaxes;
    private TaxeDto taxeDto;

}
