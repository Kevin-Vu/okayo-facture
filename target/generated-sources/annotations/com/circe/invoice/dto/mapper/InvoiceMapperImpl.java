package com.circe.invoice.dto.mapper;

import com.circe.invoice.dto.designation.DesignationDto;
import com.circe.invoice.dto.designation.DesignationDto.DesignationDtoBuilder;
import com.circe.invoice.dto.invoice.InvoiceDto;
import com.circe.invoice.dto.invoice.InvoiceDto.InvoiceDtoBuilder;
import com.circe.invoice.entity.data.DesignationEntity;
import com.circe.invoice.entity.data.InvoiceEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-02T01:25:56+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_275 (Private Build)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    private final DatatypeFactory datatypeFactory;

    public InvoiceMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public InvoiceDto convert(InvoiceEntity invoiceEntity) {
        if ( invoiceEntity == null ) {
            return null;
        }

        InvoiceDtoBuilder invoiceDto = InvoiceDto.builder();

        if ( invoiceEntity.getId() != null ) {
            invoiceDto.id( invoiceEntity.getId().longValue() );
        }
        invoiceDto.invoiceDate( xmlGregorianCalendarToString( dateToXmlGregorianCalendar( invoiceEntity.getInvoiceDate() ), null ) );
        invoiceDto.totalTaxes( invoiceEntity.getTotalTaxes() );
        invoiceDto.totalNoTaxes( invoiceEntity.getTotalNoTaxes() );
        invoiceDto.totalWithTaxes( invoiceEntity.getTotalWithTaxes() );
        invoiceDto.designations( designationEntityListToDesignationDtoList( invoiceEntity.getDesignations() ) );

        return invoiceDto.build();
    }

    @Override
    public List<InvoiceDto> convertListInvoiceEntity(List<InvoiceEntity> invoiceEntityList) {
        if ( invoiceEntityList == null ) {
            return new ArrayList<InvoiceDto>();
        }

        List<InvoiceDto> list = new ArrayList<InvoiceDto>( invoiceEntityList.size() );
        for ( InvoiceEntity invoiceEntity : invoiceEntityList ) {
            list.add( convert( invoiceEntity ) );
        }

        return list;
    }

    private String xmlGregorianCalendarToString( XMLGregorianCalendar xcal, String dateFormat ) {
        if ( xcal == null ) {
            return null;
        }

        if (dateFormat == null ) {
            return xcal.toString();
        }
        else {
            Date d = xcal.toGregorianCalendar().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat( dateFormat );
            return sdf.format( d );
        }
    }

    private XMLGregorianCalendar dateToXmlGregorianCalendar( Date date ) {
        if ( date == null ) {
            return null;
        }

        GregorianCalendar c = new GregorianCalendar();
        c.setTime( date );
        return datatypeFactory.newXMLGregorianCalendar( c );
    }

    protected DesignationDto designationEntityToDesignationDto(DesignationEntity designationEntity) {
        if ( designationEntity == null ) {
            return null;
        }

        DesignationDtoBuilder designationDto = DesignationDto.builder();

        if ( designationEntity.getId() != null ) {
            designationDto.id( designationEntity.getId().longValue() );
        }
        designationDto.name( designationEntity.getName() );
        designationDto.taxes( designationEntity.getTaxes() );
        designationDto.unitPriceNoTaxes( designationEntity.getUnitPriceNoTaxes() );
        designationDto.quantity( designationEntity.getQuantity() );
        designationDto.discount( designationEntity.getDiscount() );
        designationDto.totalNoTaxes( designationEntity.getTotalNoTaxes() );

        return designationDto.build();
    }

    protected List<DesignationDto> designationEntityListToDesignationDtoList(List<DesignationEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<DesignationDto> list1 = new ArrayList<DesignationDto>( list.size() );
        for ( DesignationEntity designationEntity : list ) {
            list1.add( designationEntityToDesignationDto( designationEntity ) );
        }

        return list1;
    }
}
