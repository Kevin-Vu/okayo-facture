package com.circe.invoice.dto.client;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String clientCode;
    private String email;
    private String authority;
    private String company;

}
