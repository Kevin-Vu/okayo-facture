package com.circe.invoice.dto.mapper;

import com.circe.invoice.dto.designation.DesignationCatalogDto;
import com.circe.invoice.entity.referential.DesignationCatalogEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DesignationCatalogMapper {

    DesignationCatalogDto convert(DesignationCatalogEntity designationCatalogEntity);

}
