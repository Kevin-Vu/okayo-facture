package com.circe.invoice.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Data
public class CirceConfiguration {

    @Value("${okayo.configuration.languages}")
    private List<String> languagesList = new ArrayList<>();

    @Value("${okayo.configuration.echeance.time}")
    private Integer echeance;

}
