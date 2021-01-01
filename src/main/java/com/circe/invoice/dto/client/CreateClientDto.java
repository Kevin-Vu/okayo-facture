package com.circe.invoice.dto.client;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientDto {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String authority;
    private String company;

}
