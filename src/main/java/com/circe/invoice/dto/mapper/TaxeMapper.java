package com.circe.invoice.dto.mapper;

import com.circe.invoice.entity.referential.ProductTypeEntity;
import com.circe.invoice.dto.taxe.TaxeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaxeMapper {

//    @Mapping(source = "productTypeEntity.name", target = "productType")
//    TaxeDto convert(ProductTypeEntity productTypeEntity);
}
