package com.circe.invoice.dto.mapper;

import com.circe.invoice.dto.designation.DesignationCatalogDto;
import com.circe.invoice.dto.designation.DesignationCatalogDto.DesignationCatalogDtoBuilder;
import com.circe.invoice.entity.referential.DesignationCatalogEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-02T01:25:56+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_275 (Private Build)"
)
@Component
public class DesignationCatalogMapperImpl implements DesignationCatalogMapper {

    @Override
    public DesignationCatalogDto convert(DesignationCatalogEntity designationCatalogEntity) {
        if ( designationCatalogEntity == null ) {
            return null;
        }

        DesignationCatalogDtoBuilder designationCatalogDto = DesignationCatalogDto.builder();

        designationCatalogDto.name( designationCatalogEntity.getName() );
        designationCatalogDto.priceNoTaxes( designationCatalogEntity.getPriceNoTaxes() );

        return designationCatalogDto.build();
    }
}
