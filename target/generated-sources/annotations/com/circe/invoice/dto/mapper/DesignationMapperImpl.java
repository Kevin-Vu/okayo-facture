package com.circe.invoice.dto.mapper;

import com.circe.invoice.dto.designation.CreateDesignationDto;
import com.circe.invoice.entity.data.DesignationEntity;
import com.circe.invoice.entity.data.DesignationEntity.DesignationEntityBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-02T01:25:57+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_275 (Private Build)"
)
@Component
public class DesignationMapperImpl implements DesignationMapper {

    @Override
    public DesignationEntity convert(CreateDesignationDto createDesignationDto) {
        if ( createDesignationDto == null ) {
            return null;
        }

        DesignationEntityBuilder designationEntity = DesignationEntity.builder();

        designationEntity.name( createDesignationDto.getName() );
        designationEntity.taxes( createDesignationDto.getTaxes() );
        designationEntity.unitPriceNoTaxes( createDesignationDto.getUnitPriceNoTaxes() );
        designationEntity.quantity( createDesignationDto.getQuantity() );
        designationEntity.discount( createDesignationDto.getDiscount() );
        designationEntity.totalNoTaxes( createDesignationDto.getTotalNoTaxes() );

        return designationEntity.build();
    }

    @Override
    public List<DesignationEntity> convertListCreateDto(List<CreateDesignationDto> createDesignationDtoList) {
        if ( createDesignationDtoList == null ) {
            return new ArrayList<DesignationEntity>();
        }

        List<DesignationEntity> list = new ArrayList<DesignationEntity>( createDesignationDtoList.size() );
        for ( CreateDesignationDto createDesignationDto : createDesignationDtoList ) {
            list.add( convert( createDesignationDto ) );
        }

        return list;
    }
}
